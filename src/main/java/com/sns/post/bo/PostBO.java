package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private FileManagerService fms;
	// userId, userLoginId, content, file
	@Autowired
	private LikeBO likeBO;
	@Autowired
	private CommentBO commentBO;
	
	public int addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = null;
		if (file != null) {
			imagePath = fms.saveFile(userLoginId, file);
		}
		
		return postDAO.insertPost(userId, content, imagePath);
	}
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public int addComment(int userId, int postId, String content) {
		return postDAO.insertComment(userId, postId, content);
	}
	
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postDAO.selectPostByPostIdUserId(postId, userId);
	}
	
	/*
	 * public List<CardView> generateCardViewList(List<CardView> cardViewList) {
	 * return postDAO.generateCardViewList(cardViewList); }
	 */
	
	public int deletePostByPostIdUserId(int postId, int userId) {
		// 기존 글 가져오기
		Post post = getPostByPostIdUserId(postId, userId);
		if (post == null) {
			logger.warn("[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
		}

		// 이미지 있으면 이미지 삭제
		if (post.getImagePath() != null) {
			fms.deleteFile(post.getImagePath());
		}

		// 글 삭제
		int postDel = postDAO.deletePostByPostIdUserId(postId, userId);
		if (postDel < 1) {
			return 0;
		}

		// 댓글들 삭제
		int commentDel = commentBO.deleteCommentByPostId(postId);
		if (commentDel < 1) {
			return 0;
		}
		// 좋아요들 삭제
		int likeDel = likeBO.deleteLikeByPostId(postId);
		if (likeDel < 1) {
			return 0;
		}
		return 1;
	}
}