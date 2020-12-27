package com.project.mstuser.Controller;

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
import com.project.mstuser.Consumer.MUserDetailConsumer;
import com.project.mstuser.DTO.MUserDTO;
import com.project.mstuser.DTO.MUserDetailDTO;
import com.project.mstuser.Entity.MUserEntity;
import com.project.mstuser.Service.MUserService;

@RestController
@RequestMapping("/mst-user")
public class MUserController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private MUserService mUserService;
	
	@Autowired
	private MUserDetailConsumer mUserDetailConsumer;
	
	@GetMapping("/{id}")
    @Timed
    public ResponseEntity<MUserDTO> findMUserById(@PathVariable("id") Long id) {
        log.info("REST API for get findMUserById by id : {}", id);
        
        Optional<MUserEntity> opt = mUserService.findById(id);
        MUserDTO mUserDTO = new MUserDTO();
    	MUserDetailDTO mUserDetailDTO = new MUserDetailDTO();
        
        if (opt.isPresent()) {
        	mUserDTO = new MUserDTO(opt.get());
        	mUserDetailDTO = mUserDetailConsumer.getMUserDetailByMUserId(mUserDTO.getId());
        	
        	log.info("port: {}",environment.getProperty("server.port"));
        	
        	mUserDTO.setMUserDetailDTO(mUserDetailDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mUserDTO);
    }
	
	@GetMapping("/")
    @Timed
    public ResponseEntity<List<MUserDTO>> findMUserAll() {
        log.info("REST API for get findMUserAll");
        
        List<MUserEntity> entities = mUserService.findAllData();
        log.info("iss: {}",entities.size());
        if (entities.size() > 0) {
        	List<MUserDTO> dtos = entities.stream().map(MUserDTO::new).collect(Collectors.toList());
        	return new ResponseEntity<List<MUserDTO>> (dtos, HttpStatus.OK);
        } else {
        	return new ResponseEntity<List<MUserDTO>> (HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping("/{page}/{pagingSize}")
    @Timed
    public ResponseEntity<List<MUserDTO>> findMUserAllPaging(@PathVariable("page") int page, @PathVariable("pagingSize") int pagingSize) {
        log.info("REST API for get findMUserAllPaging page: {} size: {}", page, pagingSize);
        
        Pageable pageable = PageRequest.of(page, pagingSize);
        Page<MUserEntity> pages = mUserService.findAllPagination(pageable);
        
        long totalElements = pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        
        List<MUserEntity> entities = pages.getContent();
        
        if (entities.size() > 0) {
        	List<MUserDTO> dtos = entities.stream().map(new Function<MUserEntity, MUserDTO>() {
  				@Override
  				public MUserDTO apply(MUserEntity entity) {
  	            	
  					MUserDTO dto = new MUserDTO(entity);
  					dto.setTotalElements(totalElements);
  					dto.setTotalPages(totalPages);
  					
  					return dto;
  				}
 			}).collect(Collectors.toList());
        	return new ResponseEntity<List<MUserDTO>> (dtos, HttpStatus.OK);
        } else {
        	return new ResponseEntity<List<MUserDTO>> (HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping("/")
    @Timed
    public ResponseEntity<?> postMUser(@RequestBody MUserDTO dto) {
        log.info("REST API for insert postMUser: {}", dto);
        
        try {
        	MUserEntity entity = new MUserEntity();
 			BeanUtils.copyProperties(dto, entity);
 			
 			mUserService.save(entity);
 			return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    }
	
	@PutMapping("/")
    @Timed
    public ResponseEntity<?>putMUser(@RequestBody MUserDTO dto) {
        log.info("REST API for update putMUser");
        
        Optional<MUserEntity> opt = mUserService.findById(dto.getId());
        
        if (!opt.isPresent()) {
 			log.error("Unable to update. M User with id {} not found",dto.getId());
 			return new ResponseEntity<>("Unable to update. M User with id " +dto.getId()+ " not found", HttpStatus.NOT_FOUND);
 		}
        
        try {
        	MUserEntity entity = new MUserEntity();
 			BeanUtils.copyProperties(dto, entity);
 			
 			mUserService.update(entity);
 			return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    }
	
	@DeleteMapping("/{id}")
    @Timed
    public ResponseEntity<?> deleteMUserById(@PathVariable("id") Long id) {
        log.info("REST API for delete deleteMUserById by id : {}", id);
        
        Optional<MUserEntity> opt = mUserService.findById(id);
        
        if (!opt.isPresent()) {
 			log.error("Unable to delete. M User with id {} not found",id);
 			return new ResponseEntity<>("Unable to delete. M User with id " +id+ " not found", HttpStatus.NOT_FOUND);
 		}
 		
        try {
        	mUserService.deleteById(id);
        	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
