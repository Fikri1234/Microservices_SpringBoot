package com.project.mstuserdetail.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.mstuserdetail.Entity.MUserDetailEntity;

public interface MUserDetailService {
	
	Optional<MUserDetailEntity> findById(Long id);
	MUserDetailEntity save(MUserDetailEntity entity);
	MUserDetailEntity update(MUserDetailEntity entity);
	List<MUserDetailEntity> findAllData();
	Page<MUserDetailEntity> findAllPagination(Pageable pageable);
	void deleteById(Long id);
	
	Optional<MUserDetailEntity> findByUserIdData(Long userId);

}
