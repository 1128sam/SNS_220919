package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;

@Repository
public interface PostDAO {
	// public List<Map<String, Object>> selectPostList();
	
	public int insertPost(
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public List<Post> selectPostList();
	
	public Post selectPostByPostIdUserId(@Param("postId") int postId, @Param("userId") int userId);
	
	public int insertComment(@Param("userId") int userId, @Param("postId") int postId, @Param("content") String content);
	
	public List<CardView> generateCardViewList(List<CardView> cardViewList);

	public void deletePostByPostIdUserId(@Param("postId") int postId, @Param("userId") int userId); 
}