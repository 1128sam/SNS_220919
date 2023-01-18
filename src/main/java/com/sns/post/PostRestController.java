package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;

	@PostMapping("/write_post")
	public Map<String, Object> writePost(@RequestParam("writeTextArea") String writeTextArea, @RequestParam("file") String file, HttpSession session) {
		Post post = postBO.addPost(writeTextArea, file);
		Map<String, Object> result = new HashMap<>();

		if (post != null) {
			result.put("result", "success");
			// 로그인 처리 - 세션에 저장(로그인 상태를 유지한다)
			session.setAttribute("content", post.getContent());
			session.setAttribute("imagePath", post.getImagePath());
		} else {
			result.put("error", "입력 실패");
		}
		return result;
	}
}