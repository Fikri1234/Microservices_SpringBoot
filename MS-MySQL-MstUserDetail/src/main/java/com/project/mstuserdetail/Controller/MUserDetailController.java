package com.project.mstuserdetail.Controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.project.mstuserdetail.DTO.MUserDetailDTO;
import com.project.mstuserdetail.Entity.MUserDetailEntity;
import com.project.mstuserdetail.Service.MUserDetailService;

@RestController
@RequestMapping("/mst-user-dtl")
public class MUserDetailController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private MUserDetailService mUserDetailService;
	
	@GetMapping("/{id}")
    @Timed
    public ResponseEntity<MUserDetailDTO> findMUserDetailById(@PathVariable("id") Long id) {
        log.info("REST API for get findMUserDetailById by id : {}", id);
        
        Optional<MUserDetailEntity> opt = mUserDetailService.findById(id);
        MUserDetailDTO dto = new MUserDetailDTO();
        
        if (opt.isPresent()) {
        	dto = new MUserDetailDTO(opt.get());
        	dto.setPort(environment.getProperty("server.port"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
	
	@GetMapping("/user/{userId}")
    @Timed
    public ResponseEntity<MUserDetailDTO> findMUserDetailByUserId(@PathVariable("userId") Long userId) {
        log.info("REST API for get findMUserDetailByUserId by id : {}", userId);
        
        Optional<MUserDetailEntity> opt = mUserDetailService.findByUserIdData(userId);
        MUserDetailDTO dto = new MUserDetailDTO();
        
        if (opt.isPresent()) {
        	dto = new MUserDetailDTO(opt.get());
        	dto.setPort(environment.getProperty("server.port"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
	
	@GetMapping("/")
    @Timed
    public ResponseEntity<List<MUserDetailDTO>> findMUserDetailAll() {
        log.info("REST API for get findMUserDetailAll");
        
        List<MUserDetailEntity> entities = mUserDetailService.findAllData();
        
        if (entities.size() > 0) {
            List<MUserDetailDTO> dtos = entities.stream().map(MUserDetailDTO::new).collect(Collectors.toList());
            
            return new ResponseEntity<List<MUserDetailDTO>>(dtos, HttpStatus.OK);
        } else {
        	return new ResponseEntity<List<MUserDetailDTO>>(HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping("/{page}/{pagingSize}")
    @Timed
    public ResponseEntity<List<MUserDetailDTO>> findMUserDetailPaging(@PathVariable("page") int page, @PathVariable("pagingSize") int pagingSize) {
        log.info("REST API for get findMUserDetailPaging page: {} size: {}", page, pagingSize);
        
        Pageable pageable = PageRequest.of(page, pagingSize);
        Page<MUserDetailEntity> pages = mUserDetailService.findAllPagination(pageable);
        
        long totalElements = pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        
        List<MUserDetailEntity> entities = pages.getContent();
        
        if (entities.size() > 0) {
        	List<MUserDetailDTO> dtos = entities.stream().map(new Function<MUserDetailEntity, MUserDetailDTO>() {
        		@Override
        		public MUserDetailDTO apply(MUserDetailEntity entity) {
        			MUserDetailDTO dto = new MUserDetailDTO(entity);
        			dto.setTotalElements(totalElements);
        			dto.setTotalPages(totalPages);
        			
        			return dto;
        		}
        	}).collect(Collectors.toList());
        	
        	return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } else {
        	return new ResponseEntity<List<MUserDetailDTO>>(HttpStatus.NOT_FOUND);
        }
        
    }
	
	@PostMapping("/")
    @Timed
    public ResponseEntity<?> postMUserDetail(@RequestBody MUserDetailDTO dto) {
		log.info("REST API for insert postMUserDetail: {}", dto);
        
        try {
        	MUserDetailEntity entity = new MUserDetailEntity();
 			BeanUtils.copyProperties(dto, entity);
 			
 			mUserDetailService.save(entity);
 			return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    }
	
	@PutMapping("/")
    @Timed
    public ResponseEntity<?> putMUserDetail(@RequestBody MUserDetailDTO dto) {
		log.info("REST API for update putMUserDetail: {}", dto);
        
		Optional<MUserDetailEntity> opt = mUserDetailService.findById(dto.getId());
        
        if (!opt.isPresent()) {
 			log.error("Unable to update. M User Detail with id {} not found",dto.getId());
 			return new ResponseEntity<>("Unable to update. M User Detail with id " +dto.getId()+ " not found", HttpStatus.NOT_FOUND);
 		}
        
        try {
        	MUserDetailEntity entity = new MUserDetailEntity();
 			BeanUtils.copyProperties(dto, entity);
 			
 			mUserDetailService.update(entity);
 			return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    }
	
	@DeleteMapping("/{id}")
    @Timed
    public ResponseEntity<?> deleteMUserDetailById(@PathVariable("id") Long id) {
		log.info("REST API for delete deleteMUserDetailById by id : {}", id);
        
        Optional<MUserDetailEntity> opt = mUserDetailService.findById(id);
        
        if (!opt.isPresent()) {
 			log.error("Unable to update. M User Detail with id {} not found",id);
 			return new ResponseEntity<>("Unable to update. M User Detail with id " +id+ " not found", HttpStatus.NOT_FOUND);
 		}
        
        try {
        	mUserDetailService.deleteById(id);
        	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
