package com.copycenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.copycenter.DAO.PrintOrderDAO;
import com.copycenter.DAO.UserAccountDAO;
import com.copycenter.pojo.PrintOrder;
import com.copycenter.pojo.StoreManager;
import com.copycenter.pojo.Student;
import com.copycenter.pojo.UserAccount;
import com.copycenter.validator.UserAccountValidator;

@Controller
public class LoginController {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private UserAccountValidator userAccountValidator;
	
	@Autowired
	private PrintOrderDAO printOrderDAO;


	
	@RequestMapping(value = "/redirectToLogin.htm", method = RequestMethod.GET)
	public String redirectToLogin(Model model, HttpServletRequest request) {
		UserAccount userAccount = new UserAccount();
		request.getSession().invalidate();
		model.addAttribute("userAccount", userAccount);
		return "home";
	}

	@RequestMapping(value = "/registration.htm", method = RequestMethod.GET)
	public String signUpPage(Model model) {

		return "registration";
	}

	@RequestMapping(value = "/loggedIn.htm", method = RequestMethod.POST)
	public String logIn(@ModelAttribute UserAccount userAccount,
			BindingResult br, HttpServletRequest request) {

		HttpSession session = request.getSession();

		userAccountValidator.validate(userAccount, br);

		if (br.hasErrors()) {
			return "home";

		} else {
			UserAccount uAccount = userAccountDAO
					.getUserAccountByUserName(userAccount.getUserName());

			if (uAccount == null) {

				return "home";

			} else {
				if (uAccount.getPassword().equals(userAccount.getPassword())) {

					if (uAccount.getRole().equals("Student")) {

						if (session.getAttribute("storeManager") != null) {
							session.invalidate();
							return "unAuthorized";
						}

						session = request.getSession(true);
						session.setAttribute("student", uAccount);
						return "studentHomePage";

					} else if (uAccount.getRole().equals("Store Manager")) {

						if (session.getAttribute("student") != null) {
							session.invalidate();
							return "unAuthorized";
						}

						session = request.getSession(true);
						session.setAttribute("storeManager", uAccount);

						return "storeManagerHomePage";
					}
				}
				return "home";
			}
		}
	}

	@RequestMapping(value = "/signUp.htm", method = RequestMethod.POST)
	public String regForm(Model model, HttpServletRequest request) {

		String formtype = null;
		String type = request.getParameter("registrationType");
		System.out.println(type);

		if (type.equals("student")) {

			Student student = new Student();
			model.addAttribute(student);

			formtype = "studentSignUp";

		} else if (type.equals("storeManager")) {

			StoreManager storeManager = new StoreManager();
			model.addAttribute(storeManager);

			formtype = "storeManagerSignUp";

		} else {

			formtype = "registration";
		}
		return formtype;
	}

	@RequestMapping(value = "/studentHome.htm", method = RequestMethod.GET)
	public String redirectToHome(
			@ModelAttribute("userAccount") UserAccount userAccount,
			Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session == null) {
			return "error";
		}
		
		PrintOrder po = (PrintOrder) session.getAttribute("orderSession");
		if (po != null) {
			PrintOrder po2 = printOrderDAO.getOrdersByOrderID(po
					.getOrderID());

			if (po2 != null) {
				if (po2.getPrintJobList().size() == 0) {
					printOrderDAO.deleteOrderAtOrderID(po2.getOrderID());
				}
			}
		}

		userAccount = (UserAccount) session.getAttribute("student");
		model.addAttribute("student", userAccount);

		return "studentHomePage";
	}
	
	@RequestMapping(value = "/storeManagerHome.htm", method = RequestMethod.GET)
	public String redirectToSMHome(
			@ModelAttribute("userAccount") UserAccount userAccount,
			Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session == null) {
			return "error";
		}

		userAccount = (UserAccount) session.getAttribute("storeManager");
		model.addAttribute("storeManager", userAccount);

		return "storeManagerHomePage";
	}

}
