package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	@Autowired
	private PostBO postBO;
	
	// @GetMapping("/create")
	@PostMapping("/create")
	public Map<String, Object> createComment(
			@RequestParam("postId") int postId,
			@RequestParam("comment") String content,
			HttpSession session
			) {
		Map <String, Object> result = new HashMap<>();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 500); // 비로그인 상태
			result.put("result", "error");
			result.put("errorMessage", "로그인을 해주세요.");
			return result;
		}
		
		postBO.addComment(userId, postId, content);

		result.put("code", 1);
		result.put("result", "성공");
		
		return result;
	}
}