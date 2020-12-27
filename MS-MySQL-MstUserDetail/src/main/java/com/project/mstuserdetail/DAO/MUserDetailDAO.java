package com.project.mstuserdetail.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.mstuserdetail.Entity.MUserDetailEntity;

@Repository
public interface MUserDetailDAO extends JpaRepository<MUserDetailEntity, Long> {
	
	Optional<MUserDetailEntity> findByUserId(Long userId);

}
