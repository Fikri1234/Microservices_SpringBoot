package com.project.mstuser.Consumer;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.mstuser.DTO.MUserDetailDTO;

@FeignClient(name="master-user-detail-service")
@RibbonClient(name="master-user-detail-service")
public interface MUserDetailConsumer {
	
	@GetMapping("api/mst-user-dtl/user/{id}")
	public MUserDetailDTO getMUserDetailByMUserId(@PathVariable Long id);

}
