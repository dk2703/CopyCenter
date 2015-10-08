package com.copycenter.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.copycenter.DAO.EstimateRequestDAO;
import com.copycenter.DAO.PrintOrderDAO;
import com.copycenter.DAO.StudentDAO;
import com.copycenter.pojo.EstimateRequest;
import com.copycenter.pojo.PrintOrder;
import com.copycenter.pojo.UserAccount;
import com.copycenter.validator.MessageValidator;

@Controller
public class EstimateRequestController {

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private EstimateRequestDAO estimateRequestDAO;
	
	@Autowired
	private MessageValidator messageValidator;
	
	@Autowired
	private PrintOrderDAO printOrderDAO;

	@RequestMapping(value = "/estimateRequest.htm", method = RequestMethod.GET)
	public String typeMessage(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session.getAttribute("student") == null
				&& (session.getAttribute("storeManager") == null)) {
			return "error";
		}

		UserAccount ua = (UserAccount) session.getAttribute("storeManger");

		if (ua != null) {
			return "unAuthorized";
		}
		EstimateRequest em = new EstimateRequest();

		model.addAttribute("estimateRequest", em);
		return "requestForm";
	}

	@RequestMapping(value = "/sendMessage.htm", method = RequestMethod.GET)
	public String sendMessage(

	@ModelAttribute("estimateRequest") EstimateRequest estimateRequest,
			Model model, HttpServletRequest request,
			BindingResult br) {
		UserAccount u = null;
		
		

		HttpSession session = request.getSession();

		if (session.getAttribute("student") == null
				&& session.getAttribute("storeManager") == null) {
			return "error";
		}

		if (session.getAttribute("student") == null) {
			u = (UserAccount) session.getAttribute("storeManager");
		} else {
			u = (UserAccount) session.getAttribute("student");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(new Date());
		estimateRequest.setSentDate(date);
		estimateRequest.setUser("Store Manager");
		estimateRequest.setFromUser(u.getUserName());

		estimateRequestDAO.saveMessage(estimateRequest);

		return "messageSent";
	}

	@RequestMapping(value = "/viewMessages.htm", method = RequestMethod.GET)
	public String viewMessage(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session.getAttribute("student") == null
				&& session.getAttribute("storeManager") == null) {
			return "error";
		}

		if (session.getAttribute("storeManager") == null) {
			return "unAuthorized";
		}

		ArrayList<EstimateRequest> requestList = estimateRequestDAO
				.getByUser("Store Manager");

		model.addAttribute("requestList", requestList);

		return "viewRequest";
	}

	@RequestMapping(value = "/replyToStu.htm", method = RequestMethod.GET)
	public String replyStu(Model model, HttpServletRequest request,

	@RequestParam("nam") String name) {

		HttpSession session = request.getSession();

		if (session.getAttribute("student") == null
				&& (session.getAttribute("storeManager") == null)) {
			return "error";
		}

		UserAccount ua = (UserAccount) session.getAttribute("student");

		if (ua != null) {
			
			return "redirect:/estimateRequest.htm";
		}
		EstimateRequest em = new EstimateRequest();

		em.setUser(name);
		session.setAttribute("replyRequest", em);
		model.addAttribute("replyRequest", em);

		return "replyFormStoreManager";
	}

	@RequestMapping(value = "/sendMessageToStu.htm", method = RequestMethod.GET)
	public String sendMessageToStu(

	@ModelAttribute("replyRequest") EstimateRequest estimateRequest,
			Model model, HttpServletRequest request) {
		UserAccount u = null;

		HttpSession session = request.getSession();
		EstimateRequest er = (EstimateRequest) session
				.getAttribute("replyRequest");

		if (session.getAttribute("student") == null
				&& session.getAttribute("storeManager") == null) {
			return "error";
		}

		if (session.getAttribute("student") == null) {
			u = (UserAccount) session.getAttribute("storeManager");
		} else {
			u = (UserAccount) session.getAttribute("student");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(new Date());

		er.setSentDate(date);
		er.setDescription(estimateRequest.getDescription());
		er.setTopic(estimateRequest.getTopic());
		er.setFromUser("Store Manager");

		estimateRequestDAO.saveMessage(er);

		return "messageSent";
	}

	@RequestMapping(value = "/viewMessagesStu.htm", method = RequestMethod.GET)
	public String viewMessageStu(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session.getAttribute("student") == null
				&& session.getAttribute("storeManager") == null) {
			return "error";
		}

		if (session.getAttribute("student") == null) {
			return "unAuthorized";
		}

		UserAccount u = (UserAccount) session.getAttribute("student");

		ArrayList<EstimateRequest> requestList = estimateRequestDAO
				.getByFromUser("Store Manager", u.getUserName());

		model.addAttribute("requestList", requestList);

		return "viewRequestStudent";
	}
}
