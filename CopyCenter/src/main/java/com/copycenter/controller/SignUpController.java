package com.copycenter.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.copycenter.DAO.StoreManagerDAO;
import com.copycenter.DAO.StudentDAO;
import com.copycenter.DAO.UserAccountDAO;
import com.copycenter.pojo.StoreManager;
import com.copycenter.pojo.Student;
import com.copycenter.pojo.UserAccount;
import com.copycenter.validator.StoreManagerValidator;
import com.copycenter.validator.StudentValidator;

@Controller
public class SignUpController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private StudentValidator studentValidator;

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private StoreManagerDAO storeManagerDAO;

	@Autowired
	private StoreManagerValidator managerValidator;

	/*---------------------------------------------------------------------*/

	@RequestMapping(value = "/studentReg.htm", method = RequestMethod.POST)
	public String studentRegistration(
			@ModelAttribute("student") Student student,
			BindingResult bindingResult, Model model) {

		studentValidator.validate(student, bindingResult);

		if (bindingResult.hasErrors()) {
			student = new Student();

			return "studentSignUp";
		} else {

			UserAccount userAccount = student.getUserAccount();

			/* check username and check is email id is husky id code */

			userAccountDAO.saveUserAccount(userAccount);

			studentDAO.saveStudent(student);

			model.addAttribute("student", student);

			return "regSuccess";

		}

	}

	@RequestMapping(value = "/storeManagerReg.htm", method = RequestMethod.POST)
	public String storeManagerRegistration(
			@ModelAttribute("storeManager") StoreManager storeManager,
			BindingResult bindingResult, Model model) {

		managerValidator.validate(storeManager, bindingResult);

		if (bindingResult.hasErrors()) {
			storeManager = new StoreManager();

			return "storeManagerSignUp";
		} else {

			UserAccount userAccount = storeManager.getUserAccount();

			/* check username and check is email id is husky id code */

			userAccountDAO.saveUserAccount(userAccount);

			storeManagerDAO.saveStoreManager(storeManager);

			model.addAttribute("storeManager", storeManager);

			return "regSuccess";

		}

	}

}
