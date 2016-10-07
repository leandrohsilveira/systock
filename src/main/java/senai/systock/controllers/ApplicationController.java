package senai.systock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

	@RequestMapping("/")
	public String app() {
		return "forward:app.html";
	}
	
}
