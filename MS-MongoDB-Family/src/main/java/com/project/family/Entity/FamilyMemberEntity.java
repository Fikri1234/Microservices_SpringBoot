package com.project.family.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Document(collection = "family_member")
public class FamilyMemberEntity {
	
	@Id
	private String id;
	private Long userId;
	private FamilyMemberWifeEntity wife;
	private List<FamilyMemberChildsEntity> childs;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;

}
