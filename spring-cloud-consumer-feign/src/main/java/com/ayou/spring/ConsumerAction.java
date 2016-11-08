package com.ayou.spring;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConsumerAction {
	@Autowired
	ComputeClient computeClient;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Integer add() {
		return computeClient.add(10, 20);
	}

	@RequestMapping(value = "/first", method = RequestMethod.GET)
	public Map<String, Object> firstResp(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		request.getSession().setAttribute("url", request.getRequestURL());
		map.put("request Url", request.getRequestURL());
		return map;
	}

	@RequestMapping(value = "/sessions", method = RequestMethod.GET)
	public Object sessions(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", request.getSession().getId());
		map.put("message", request.getSession().getAttribute("url"));
		return map;
	}
	
	@RequestMapping(value = "/clean", method = RequestMethod.GET)
	public Object clean(HttpServletRequest request) {
		request.getSession().removeAttribute("url");
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", request.getSession().getId());
		map.put("message", request.getSession().getAttribute("url"));
		return map;
	}
}
