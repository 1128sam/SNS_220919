package com.sns.user.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
	public int existLoginId(String loginId);
}