package com.sns.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {
	public List<Map<String, Object>> selectPostList();
	
	public Post insertPost(@Param("writeTextArea") String writeTextArea, @Param("file") String file);
}