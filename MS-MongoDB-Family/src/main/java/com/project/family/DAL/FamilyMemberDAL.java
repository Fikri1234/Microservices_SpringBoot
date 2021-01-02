package com.project.family.DAL;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.family.Entity.FamilyMemberEntity;

@Repository
public interface FamilyMemberDAL extends MongoRepository<FamilyMemberEntity, String>, FamilyMemberCustomDAL {
	
	Optional<FamilyMemberEntity> findByUserId(Long userId);
	
	// projection
	@Query(value = "{}", fields = "{modifiedBy : 0, modifiedDate : 0}")
	List<FamilyMemberEntity> findAllExcludeModifiedByModifiedDate();
	
}
