package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private UserBO userBO;
	
	public List<Comment> getCommentList() {
		return commentDAO.selectCommentList();
	}
	
	public List<Comment> getCommentByPostId(int postId) {
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	// input: 글번호 / output: 글번호에 해당하는 댓글 목록 + 댓글쓴이 정보)
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		// 결과물
		List<CommentView> commentViewList = new ArrayList<>();

		// 댓글 목록
		List<Comment> commentList = commentDAO.selectCommentListByPostId(postId);
		
		// 반복문 => commentview
//		for (int i = 0; i < commentList.size(); i++) {
//			CommentView cv = new CommentView();
//			Comment comment = cv.setComment(commentList.get(i));
//			commentList.get(i)
//		}
		
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			commentView.setComment(comment);
			
			User user = userBO.getUserById(comment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		
		// 결과물 리턴
		return commentViewList;
	}
	
	public void deleteCommentByPostId(int postId) {
		commentDAO.deleteCommentByPostId(postId);
	}
}