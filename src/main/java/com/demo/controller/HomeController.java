package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.service.HomeService;

@Controller
@RequestMapping
public class HomeController {
	{
		System.out.println("Loading HomeController");
	}
	
	@Autowired
	HomeService homeService;

	@RequestMapping(value="/welcome/{message}", method=RequestMethod.GET)
	public ModelAndView welcome(@PathVariable String message) throws Exception {
		System.out.println("Inside welcome- HomeController");
		ModelAndView mv= new ModelAndView();
		mv.addObject("message","Welcome to MVC Application");
		mv.setViewName("welcome1");
		String messageFromService= homeService.postMessage(message);
		System.out.println("**************  outgoing Message :  "+messageFromService);
		return mv;
	}
	@RequestMapping(value="/readMessage", method=RequestMethod.GET)
	public ModelAndView readMessage() throws Exception {
		System.out.println("Inside welcome- HomeController");
		ModelAndView mv= new ModelAndView();
		mv.addObject("message","Welcome to MVC Application");
		mv.setViewName("welcome1");
		String messageReadFromService= homeService.publishMessage();
		System.out.println("**************  consuming Message :  "+messageReadFromService);
		mv.addObject("jms_message_consumed",messageReadFromService);
		return mv;
	}
	
	
}
