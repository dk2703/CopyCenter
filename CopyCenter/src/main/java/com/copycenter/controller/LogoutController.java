package com.copycenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.copycenter.DAO.PrintOrderDAO;
import com.copycenter.pojo.PrintOrder;
import com.copycenter.pojo.UserAccount;

@Controller
public class LogoutController {

	@Autowired
	private PrintOrderDAO printOrderDAO;

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		if (session.getAttribute("student") == null
				&& session.getAttribute("storeManager") == null) {
			return "invalid";
		}

		if (session.getAttribute("student") != null) {

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
		}
		UserAccount userAccount = new UserAccount();
		session.invalidate();

		model.addAttribute("userAccount", userAccount);

		return "home";
	}
}
