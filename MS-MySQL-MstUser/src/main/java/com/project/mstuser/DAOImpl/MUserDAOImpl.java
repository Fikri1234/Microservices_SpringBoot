package com.project.mstuser.DAOImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.mstuser.DAO.MUserDAO;
import com.project.mstuser.Entity.MUserEntity;
import com.project.mstuser.Service.MUserService;

@Service
@Transactional
public class MUserDAOImpl implements MUserService {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MUserDAO mUserDAO;
	
	@Override
	public Optional<MUserEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return mUserDAO.findById(id);
	}

	@Override
	public MUserEntity save(MUserEntity entity) {
		// TODO Auto-generated method stub
		return mUserDAO.save(entity);
	}

	@Override
	public MUserEntity update(MUserEntity entity) {
		// TODO Auto-generated method stub
		return save(entity);
	}

	@Override
	public List<MUserEntity> findAllData() {
		// TODO Auto-generated method stub
		return mUserDAO.findAll();
	}

	@Override
	public Page<MUserEntity> findAllPagination(Pageable pageable) {
		// TODO Auto-generated method stub
		return mUserDAO.findAll(pageable);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		mUserDAO.deleteById(id);
	}

}
