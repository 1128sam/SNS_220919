package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.Comment;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

@Controller
public class TimelineController {
	@Autowired
	private PostBO postBO;
	@Autowired
	private CommentBO commentBO;

	// http://localhost:8080/timeline/timeline_view
	@GetMapping("/timeline/timeline_view")
	public String timelineView(Model model) {
		List<Post> postList = postBO.getPostList();
		model.addAttribute("postList", postList);

		// HashMap<List<Comment>> commentList =
		List<Comment> commentList = commentBO.getCommentList();
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}