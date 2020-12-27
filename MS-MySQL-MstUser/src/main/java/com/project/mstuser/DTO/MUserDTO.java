package com.project.mstuser.DTO;

import com.project.mstuser.Entity.MUserEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MUserDTO {
	
	private Long id;
	private String username;
	private String password;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	private boolean enabled;
	private Long userDetailId;
	private MUserDetailDTO mUserDetailDTO;
	
	private long totalElements;
	private int totalPages;
	
	public MUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MUserDTO(Long id, String username, String password, boolean accountExpired, boolean accountLocked,
			boolean credentialsExpired, boolean enabled, Long userDetailId, long totalElements, int totalPages) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
		this.credentialsExpired = credentialsExpired;
		this.enabled = enabled;
		this.userDetailId = userDetailId;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

	public MUserDTO(MUserEntity entity) {
		super();
		this.id = entity.getId();
		this.username = entity.getUsername();
		this.password = entity.getPassword();
		this.accountExpired = entity.isAccountExpired();
		this.accountLocked = entity.isAccountLocked();
		this.credentialsExpired = entity.isCredentialsExpired();
		this.enabled = entity.isEnabled();
		this.userDetailId = entity.getUserDetailId();
	}

}
