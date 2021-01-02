package com.project.mstuserdetail.Consumer;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.mstuserdetail.DTO.FamilyMemberDTO;

@FeignClient(name="family-member-service")
@RibbonClient(name="family-member-service")
public interface FamilyMemberConsumer {
	
	@GetMapping("api/family-member/userId/{id}")
	public FamilyMemberDTO findByUserId(@PathVariable Long id);

}
