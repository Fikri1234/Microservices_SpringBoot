package com.project.mstuser.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.mstuser.Entity.MUserEntity;

@Repository
public interface MUserDAO extends JpaRepository<MUserEntity, Long> {

}
