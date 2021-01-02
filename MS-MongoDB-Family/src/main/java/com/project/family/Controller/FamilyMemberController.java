package com.project.family.Controller;

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

import com.project.family.DTO.FamilyMemberDTO;
import com.project.family.Entity.FamilyMemberEntity;
import com.project.family.Service.FamilyMemberService;

@RestController
@RequestMapping("/family-member")
public class FamilyMemberController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	FamilyMemberService familyMemberService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/{id}")
    public ResponseEntity<FamilyMemberDTO> findFamilyMemberById(@PathVariable("id") String id) {
        log.info("REST API for get findFamilyMemberById by id : {}", id);
        
        Optional<FamilyMemberEntity> opt = familyMemberService.findById(id);
        FamilyMemberDTO familyMemberDTO = new FamilyMemberDTO();
        
        if (opt.isPresent()) {
        	familyMemberDTO = new FamilyMemberDTO(opt.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(familyMemberDTO);
    }
	
	@PostMapping("/")
    public ResponseEntity<?> postFamilyMember(@RequestBody FamilyMemberDTO dto) {
        log.info("REST API for insert postFamilyMember: {}", dto);
        
        try {
        	FamilyMemberEntity entity = new FamilyMemberEntity();
 			BeanUtils.copyProperties(dto, entity);
 			
 			familyMemberService.save(entity);
 			return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    }
	
	@PutMapping("/")
    public ResponseEntity<?> putFamilyMember(@RequestBody FamilyMemberDTO dto) {
        log.info("REST API for update putFamilyMember: {}", dto);
        
        Optional<FamilyMemberEntity> opt = familyMemberService.findById(dto.getId());
        
        if (!opt.isPresent()) {
 			log.error("Unable to update. Family Member with id {} not found",dto.getId());
 			return new ResponseEntity<>("Unable to update. Family Member with id " +dto.getId()+ " not found", HttpStatus.NOT_FOUND);
 		}
        
        try {
        	FamilyMemberEntity entity = new FamilyMemberEntity();
 			BeanUtils.copyProperties(dto, entity);
 			
 			familyMemberService.update(entity);
 			return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    }
	
	@GetMapping("/")
    public ResponseEntity<List<FamilyMemberDTO>> findFamilyMemberAll() {
        log.info("REST API for get findFamilyMemberAll");
        
        List<FamilyMemberEntity> entities = familyMemberService.findAllData();
        log.info("iss: {}",entities.size());
        if (entities.size() > 0) {
        	List<FamilyMemberDTO> dtos = entities.stream().map(FamilyMemberDTO::new).collect(Collectors.toList());
        	return new ResponseEntity<List<FamilyMemberDTO>> (dtos, HttpStatus.OK);
        } else {
        	return new ResponseEntity<List<FamilyMemberDTO>> (HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping("/{page}/{pagingSize}")
    public ResponseEntity<List<FamilyMemberDTO>> findFamilyMemberAllPaging(@PathVariable("page") int page, @PathVariable("pagingSize") int pagingSize) {
        log.info("REST API for get findFamilyMemberAllPaging page: {} size: {}", page, pagingSize);
        
        Pageable pageable = PageRequest.of(page, pagingSize);
        Page<FamilyMemberEntity> pages = familyMemberService.findAllPagination(pageable);
        
        long totalElements = pages.getTotalElements();
        int totalPages = pages.getTotalPages();
        
        List<FamilyMemberEntity> entities = pages.getContent();
        
        if (entities.size() > 0) {
        	List<FamilyMemberDTO> dtos = entities.stream().map(new Function<FamilyMemberEntity, FamilyMemberDTO>() {
  				@Override
  				public FamilyMemberDTO apply(FamilyMemberEntity entity) {
  	            	
  					FamilyMemberDTO dto = new FamilyMemberDTO(entity);
  					dto.setTotalElements(totalElements);
  					dto.setTotalPages(totalPages);
  					
  					return dto;
  				}
 			}).collect(Collectors.toList());
        	return new ResponseEntity<List<FamilyMemberDTO>> (dtos, HttpStatus.OK);
        } else {
        	return new ResponseEntity<List<FamilyMemberDTO>> (HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFamilyMemberById(@PathVariable("id") String id) {
        log.info("REST API for delete deleteFamilyMemberById by id : {}", id);
        
        Optional<FamilyMemberEntity> opt = familyMemberService.findById(id);
        
        if (!opt.isPresent()) {
 			log.error("Unable to delete. Family Member with id {} not found",id);
 			return new ResponseEntity<>("Unable to delete. Family Member with id " +id+ " not found", HttpStatus.NOT_FOUND);
 		}
 		
        try {
        	familyMemberService.deleteById(id);
        	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("userId/{id}")
    public ResponseEntity<FamilyMemberDTO> findByUserId(@PathVariable("id") Long userId) {
        log.info("REST API for get findFamilyMemberById by id : {}", userId);
        
        Optional<FamilyMemberEntity> opt = familyMemberService.findByUserIdData(userId);
        FamilyMemberDTO familyMemberDTO = new FamilyMemberDTO();
        
        if (opt.isPresent()) {
        	familyMemberDTO = new FamilyMemberDTO(opt.get());
        	familyMemberDTO.setPort(environment.getProperty("server.port"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(familyMemberDTO);
    }
	
	@GetMapping("userId/childs/first-name/{firstName}")
    public ResponseEntity<List<FamilyMemberDTO>> findByChildsFirstName(@PathVariable("firstName") String firstName) {
        log.info("REST API for get findByChildsFirstName by id : {}", firstName);
        
        List<FamilyMemberEntity> entities = familyMemberService.findByChildsFirstName(firstName);
        
        log.info("iss: {}",entities.size());
        if (entities.size() > 0) {
        	List<FamilyMemberDTO> dtos = entities.stream().map(FamilyMemberDTO::new).collect(Collectors.toList());
        	return new ResponseEntity<List<FamilyMemberDTO>> (dtos, HttpStatus.OK);
        } else {
        	return new ResponseEntity<List<FamilyMemberDTO>> (HttpStatus.NOT_FOUND);
        }
    }

}
