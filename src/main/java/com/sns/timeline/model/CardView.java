package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.like.model.Like;
import com.sns.post.model.Post;
import com.sns.user.model.User;

// View용 객체 (포스트 하나 모양)
public class CardView {
	// 글 1개
	private Post post;

	// 글쓴이 정보
	private User user;

	// 댓글들 N개
	private List<CommentView> comment;

	// 내가(로그인된 사람) 좋아요를 눌렀는지 (boolean)
	private boolean filledLike;

	// 좋아요 개수
	private Like like;


	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommentView> getComment() {
		return comment;
	}

	public void setComment(List<CommentView> comment) {
		this.comment = comment;
	}

	public Like getLike() {
		return like;
	}

	public void setLike(Like like) {
		this.like = like;
	}
}