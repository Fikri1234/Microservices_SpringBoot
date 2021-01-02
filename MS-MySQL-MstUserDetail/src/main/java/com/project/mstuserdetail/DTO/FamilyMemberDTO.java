package com.project.mstuserdetail.DTO;

import java.util.List;

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

}
