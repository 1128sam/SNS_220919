package com.sns.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;

@Repository
public interface CommentDAO {
	public List<Comment> selectCommentList();
}