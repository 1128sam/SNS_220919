package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private FileManagerService fms;
	// userId, userLoginId, content, file
	
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
	
	/*
	 * public List<CardView> generateCardViewList(List<CardView> cardViewList) {
	 * return postDAO.generateCardViewList(cardViewList); }
	 */
}