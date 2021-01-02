package com.project.family.DALImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.family.DAL.FamilyMemberDAL;
import com.project.family.Entity.FamilyMemberEntity;
import com.project.family.Service.FamilyMemberService;

@Service
@Transactional
public class FamilyMemberDALImpl implements FamilyMemberService {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	FamilyMemberDAL familyMemberDAL;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Optional<FamilyMemberEntity> findById(String id) {
		// TODO Auto-generated method stub
		return familyMemberDAL.findById(id);
	}

	@Override
	public FamilyMemberEntity save(FamilyMemberEntity entity) {
		// TODO Auto-generated method stub
		return familyMemberDAL.save(entity);
	}

	@Override
	public FamilyMemberEntity update(FamilyMemberEntity entity) {
		// TODO Auto-generated method stub
		return save(entity);
	}

	@Override
	public List<FamilyMemberEntity> findAllData() {
		// TODO Auto-generated method stub
		return familyMemberDAL.findAll();
	}

	@Override
	public Page<FamilyMemberEntity> findAllPagination(Pageable pageable) {
		// TODO Auto-generated method stub
		return familyMemberDAL.findAll(pageable);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		familyMemberDAL.deleteById(id);
	}

	@Override
	public Optional<FamilyMemberEntity> findByUserIdData(Long userId) {
		// TODO Auto-generated method stub
		return familyMemberDAL.findByUserId(userId);
	}

	@Override
	public List<FamilyMemberEntity> findByChildsFirstName(String firstName) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("childs.firstName").regex(firstName));
		query.fields().include("childs.$");
		query.fields().include("userId");
		query.fields().include("wife");
		return mongoTemplate.find(query, FamilyMemberEntity.class);
	}

	@Override
	public List<FamilyMemberEntity> findByChildsGender(String gender) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("childs.gender").is(gender));
		query.fields().include("childs.$");
		return mongoTemplate.find(query, FamilyMemberEntity.class);
	}

}
