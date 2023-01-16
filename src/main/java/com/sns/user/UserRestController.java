package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.user.model.bo.UserBO;

@RequestMapping("/user")
@RestController
public class UserRestController {
	private UserBO userBO;
	
	@RequestMapping("is_duplicated_id")
	public Map<String, Object> isDuplicatedId(@RequestParam("loginId") String loginId) {
		boolean exist = false;
		System.out.println(exist);
		Map<String, Object> result = new HashMap<>();
		if (exist) {
			result.put("code", 1);
			result.put("result", true);
		} else {
			result.put("code", 1);
			result.put("result", false);
		}
		return result;
	}
}