package com.copycenter.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.copycenter.DAO.PrintOrderDAO;
import com.copycenter.DAO.StudentDAO;
import com.copycenter.pojo.PrintOrder;
import com.copycenter.pojo.Student;
import com.copycenter.pojo.UserAccount;

@Controller
public class ViewOrdersController {

	@Autowired
	private PrintOrderDAO printOrderDAO;

	@Autowired
	private StudentDAO studentDAO;

	@RequestMapping(value = "/viewOrders.htm", method = RequestMethod.GET)
	public String viewOrderStoreManger(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();

		if ((session.getAttribute("student") == null)
				&& (session.getAttribute("storeManager") == null)) {

			return "error";
		}

		UserAccount userAccount = (UserAccount) session.getAttribute("student");

		if (userAccount != null) {
			return "unAuthorized";
		}

		// UserAccount userAccount2 = (UserAccount) session
		// .getAttribute("storeManager");

		ArrayList<PrintOrder> orderList = printOrderDAO.getAllOrders();

		model.addAttribute("orderList", orderList);
		session.setAttribute("orderList", orderList);

		return "viewOrders";
		
		
	}

	@RequestMapping(value = "/complete.htm", method = RequestMethod.GET)
	public String setStatusOfOrder(@RequestParam("print") int orderid,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		if ((session.getAttribute("student") == null)
				&& (session.getAttribute("storeManager") == null)) {

			return "error";
		}

		UserAccount userAccount = (UserAccount) session.getAttribute("student");

		if (userAccount != null) {
			return "unAuthorized";
		}
		
		UserAccount ua2 = (UserAccount)session.getAttribute("storeManager");

		printOrderDAO.statusUpdatedBy(ua2.getUserAccountID());
		printOrderDAO.setStatus(orderid, "Completed");

		return "redirect:viewOrders.htm";
	}

	@RequestMapping(value = "/viewStuOrders.htm", method = RequestMethod.GET)
	public String viewStudentOrders(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();

		if ((session.getAttribute("student") == null)
				&& (session.getAttribute("storeManager") == null)) {

			return "error";
		}

		UserAccount userAccount = (UserAccount) session
				.getAttribute("storeMAnager");

		if (userAccount != null) {
			return "unAuthorized";
		}
		UserAccount u2 = (UserAccount) session.getAttribute("student");
		

		Student s = studentDAO.getStudent(u2);

		ArrayList<PrintOrder> orderList = printOrderDAO.getOrdersByUser(s
				.getStudentID());

		model.addAttribute("orderList", orderList);
		session.setAttribute("orderList", orderList);

		return "viewStuOrders";
	}

	@RequestMapping(value = "/cancel.htm", method = RequestMethod.GET)
	public String cancelOrder(@RequestParam("print") int orderid, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();

		if ((session.getAttribute("student") == null)
				&& (session.getAttribute("storeManager") == null)) {

			return "error";
		}

		UserAccount userAccount = (UserAccount) session.getAttribute("student");

		if (userAccount == null) {
			return "unAuthorized";
		}

		printOrderDAO.setStatus(orderid, "Cancelled");

		return "redirect:viewStuOrders.htm";
	}

}
