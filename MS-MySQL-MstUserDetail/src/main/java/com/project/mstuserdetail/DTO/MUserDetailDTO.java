package com.project.mstuserdetail.DTO;

import com.project.mstuserdetail.Entity.MUserDetailEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MUserDetailDTO {
	
	private Long id;
	private String address;
	private String dob;
	private String pob;
	private String phone;
	private String email;
	private Long userId;
	private String port;
	
	private FamilyMemberDTO familyMemberDTO;
	
	private long totalElements;
	private int totalPages;
	
	public MUserDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MUserDetailDTO(Long id, String address, String dob, String pob, String phone, String email, Long userId,
			long totalElements, int totalPages) {
		super();
		this.id = id;
		this.address = address;
		this.dob = dob;
		this.pob = pob;
		this.phone = phone;
		this.email = email;
		this.userId = userId;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

	public MUserDetailDTO(MUserDetailEntity entity) {
		super();
		this.id = entity.getId();
		this.address = entity.getAddress();
		this.dob = entity.getDob();
		this.pob = entity.getPob();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
		this.userId = entity.getUserId();
	}

}
