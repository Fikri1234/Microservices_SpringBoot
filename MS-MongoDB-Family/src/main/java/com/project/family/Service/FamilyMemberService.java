package com.project.family.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.family.Entity.FamilyMemberEntity;

public interface FamilyMemberService {
	
	Optional<FamilyMemberEntity> findById(String id);
	FamilyMemberEntity save(FamilyMemberEntity entity);
	FamilyMemberEntity update(FamilyMemberEntity entity);
	List<FamilyMemberEntity> findAllData();
	Page<FamilyMemberEntity> findAllPagination(Pageable pageable);
	void deleteById(String id);
	
	Optional<FamilyMemberEntity> findByUserIdData(Long userId);
	List<FamilyMemberEntity> findByChildsFirstName(String firstName);
	List<FamilyMemberEntity> findByChildsGender(String gender);

}
