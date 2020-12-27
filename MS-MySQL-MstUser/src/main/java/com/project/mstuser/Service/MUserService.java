package com.project.mstuser.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.mstuser.Entity.MUserEntity;

public interface MUserService {
	
	Optional<MUserEntity> findById(Long id);
	MUserEntity save(MUserEntity entity);
	MUserEntity update(MUserEntity entity);
	List<MUserEntity> findAllData();
	Page<MUserEntity> findAllPagination(Pageable pageable);
	void deleteById(Long id);

}
