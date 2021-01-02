package com.project.family.DAL;

import java.util.List;

import com.project.family.Entity.FamilyMemberEntity;

public interface FamilyMemberCustomDAL {
	
	List<FamilyMemberEntity> findByChildsFirstName(String firstName);
	List<FamilyMemberEntity> findByChildsGender(String gender);

}
