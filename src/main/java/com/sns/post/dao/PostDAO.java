package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {
	// public List<Map<String, Object>> selectPostList();
	
	public int insertPost(
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public List<Post> selectPostList();
	
	public int insertComment(@Param("userId") int userId, @Param("postId") int postId, @Param("content") String content);
}