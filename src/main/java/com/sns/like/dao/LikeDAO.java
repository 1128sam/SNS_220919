package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	public boolean insertLike(@Param("userId") Integer userId, @Param("postId") int postId);
}