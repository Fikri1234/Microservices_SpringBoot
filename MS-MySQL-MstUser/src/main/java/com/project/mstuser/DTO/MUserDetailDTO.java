package com.project.mstuser.DTO;

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
public class MUserDetailDTO {
	
	private Long id;
	private String address;
	private String dob;
	private String pob;
	private String phone;
	private String email;
	private String userId;
	private String port;
	
	private long totalElements;
	private int totalPages;

}
