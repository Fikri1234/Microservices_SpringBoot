package com.project.mstuserdetail.DAOImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.mstuserdetail.DAO.MUserDetailDAO;
import com.project.mstuserdetail.Entity.MUserDetailEntity;
import com.project.mstuserdetail.Service.MUserDetailService;

@Service
@Transactional
public class MUserDetailDAOImpl implements MUserDetailService {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MUserDetailDAO mUserDetailDAO;

	@Override
	public Optional<MUserDetailEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return mUserDetailDAO.findById(id);
	}

	@Override
	public MUserDetailEntity save(MUserDetailEntity entity) {
		// TODO Auto-generated method stub
		return mUserDetailDAO.save(entity);
	}

	@Override
	public MUserDetailEntity update(MUserDetailEntity entity) {
		// TODO Auto-generated method stub
		return save(entity);
	}

	@Override
	public List<MUserDetailEntity> findAllData() {
		// TODO Auto-generated method stub
		return mUserDetailDAO.findAll();
	}

	@Override
	public Page<MUserDetailEntity> findAllPagination(Pageable pageable) {
		// TODO Auto-generated method stub
		return mUserDetailDAO.findAll(pageable);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		mUserDetailDAO.deleteById(id);
	}

	@Override
	public Optional<MUserDetailEntity> findByUserIdData(Long userId) {
		// TODO Auto-generated method stub
		return mUserDetailDAO.findByUserId(userId);
	}

}
