package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;

//	public boolean addLike(Integer userId, int postId) {
//		return likeDAO.insertLike(userId, postId);
//	}
	
	public void likeToggle(int postId, int userId) {
		// 좋아요 있는지 확인
		if (likeDAO.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
			// 있으면 제거
			likeDAO.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// 없으면 추가
			likeDAO.insertLike(postId, userId);
		}
	}
	
//	public boolean existLike(int postId, Integer userId) {
//		if (userId == null) {
//			return false;
//		} else {
//		return likeDAO.existLike(postId, userId); // 로그인
//		}
//	}
	
//	public int getLikeCountByPostId(int postId) {
//		return likeDAO.getLikeCountByPostId(postId);
//	}
	
	public boolean existLike(int postId, Integer userId) {
		if (userId == null) {
			return false;
		} else
		return likeDAO.selectLikeCountByPostIdOrUserId(postId, userId) > 0 ? true : false; // 로그인
	}
	
	public int getLikeCountByPostId(int postId) {
		return likeDAO.selectLikeCountByPostIdOrUserId(postId, null);
	}
}