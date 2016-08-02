package com.test.webapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap m) {
		m.put("welcome", "Hello thymeleaf");
		return "index";
	}

	@RequestMapping(value = "/page/1", method = RequestMethod.GET)
	public String page1(ModelMap m) {
		m.put("welcome", "Hello page1");
		return "pages/page1";
	}

	@RequestMapping(value = "/page/2", method = RequestMethod.GET)
	public String page2(ModelMap m) {
		m.put("welcome", "Hello page2");
		return "pages/page2";
	}
}
