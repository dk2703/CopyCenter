package com.copycenter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.copycenter.pojo.UserAccount;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage(ModelAndView model, HttpServletRequest request) {

		UserAccount userAccount = new UserAccount();

		return new ModelAndView("home", "userAccount", userAccount);
	}

	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView model, HttpServletRequest request) {

		UserAccount userAccount = new UserAccount();

		return new ModelAndView("home", "userAccount", userAccount);
	}

	@RequestMapping(value = "/aboutus.htm", method = RequestMethod.GET)
	public ModelAndView aboutus(ModelAndView model, HttpServletRequest request) {
		
		UserAccount userAccount = new UserAccount();

		return new ModelAndView("aboutus", "userAccount", userAccount);
	}
}
