package com.sns.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;

@Repository
public interface CommentDAO {
	public List<Comment> selectCommentList();

	public List<Comment> selectCommentListByPostId(int postId);
	
	public List<CommentView> generateCommentViewListByPostId(int postId);
	
	public void deleteCommentByPostId(int postId);
}