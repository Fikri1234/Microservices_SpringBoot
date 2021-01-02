package com.project.family.DTO;

import java.util.List;

import com.project.family.Entity.FamilyMemberChildsEntity;
import com.project.family.Entity.FamilyMemberEntity;
import com.project.family.Entity.FamilyMemberWifeEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FamilyMemberDTO {
	
	private String id;
	private Long userId;
	private FamilyMemberWifeEntity wife;
	private List<FamilyMemberChildsEntity> childs;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	
	private String port;
	
	private long totalElements;
	private int totalPages;
	
	public FamilyMemberDTO(FamilyMemberEntity entity) {
		super();
		this.id = entity.getId();
		this.userId = entity.getUserId();
		this.wife = entity.getWife();
		this.childs = entity.getChilds();
		this.createdBy = entity.getCreatedBy();
		this.createdDate = entity.getCreatedDate();
		this.modifiedBy = entity.getModifiedBy();
		this.modifiedDate = entity.getModifiedDate();
	}

}
