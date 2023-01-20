package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimeLineBO {
	
	@Autowired
	private PostBO postBO;
	@Autowired
	private UserBO userBO;
	@Autowired
	private CommentBO commentBO;

	// 로그인되지 않은 사람도 카드 목록이 보여야 한다.
	public List<CardView> generateCardList() {
		List<CardView> cardViewList = new ArrayList<>();

		// 글목록 가져오기(post)
		List<Post> postList = postBO.getPostList();
		
		// postList 반복문 => Cardview => cardViewList에 넣는다.
//		for (int i = 0; i < postList.size(); i++) {
//			CardView cv = new CardView();
//			cv.setPost(postList.get(i));
//			cardViewList.add(cv);
//		}
		for (Post post : postList) {
			CardView card = new CardView();
			
			card.setPost(post);
			
			User user = userBO.getUserById(post.getUserId());
			card.setUser(user);
			
			List<CommentView> comment = commentBO.generateCommentViewListByPostId(post.getId());
			card.setComment(comment);
			
			cardViewList.add(card);
		}
		return cardViewList;
//		return postBO.generateCardViewList(cardViewList);
	}
}