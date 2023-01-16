package com.sns.user.model.bo;

import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;

@Service
public class UserBO {
	private UserDAO userDAO;

	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}
}