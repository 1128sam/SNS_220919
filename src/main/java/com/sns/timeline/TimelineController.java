package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.bo.TimeLineBO;
import com.sns.timeline.model.CardView;

@Controller
public class TimelineController {
	@Autowired
	private PostBO postBO;
	@Autowired
	private CommentBO commentBO;
	@Autowired
	private TimeLineBO tlBO;

	// http://localhost:8080/timeline/timeline_view
	@GetMapping("/timeline/timeline_view")
	public String timelineView(Model model) {
		List<Post> postList = postBO.getPostList();
//		model.addAttribute("postList", postList);

		List<CardView> cardList = tlBO.generateCardList();
		model.addAttribute("cardList", cardList);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}