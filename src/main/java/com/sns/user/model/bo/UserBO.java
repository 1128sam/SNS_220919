package com.sns.user.model.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;

	public int existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
}