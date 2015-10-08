package com.copycenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.copycenter.DAO.PrintJobDAO;
import com.copycenter.DAO.PrintOrderDAO;
import com.copycenter.pojo.PrintJob;
import com.copycenter.pojo.PrintOrder;
import com.copycenter.pojo.UserAccount;

@Controller
public class PrintJobController {
	
	@Autowired
	private PrintOrderDAO printOrderDAO;
	
	@Autowired
	private PrintJobDAO printJobDAO;

	@RequestMapping(value = "/newPrintJob.htm", method = RequestMethod.GET)
	public String newPrintJob(
			@ModelAttribute("printOrder") PrintOrder printOrder1, Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		PrintOrder printOrder = (PrintOrder)session.getAttribute("orderSession");
		
		System.out.println(printOrder1.getOrderID());
		System.out.println(printOrder.getOrderID());

		if (session.getAttribute("student") == null
				&& (session.getAttribute("storeManger") == null)) {
			return "error";
		}

		UserAccount account = (UserAccount) session
				.getAttribute("storeManager");

		if (account != null) {
			return "error";
		}
		

		PrintJob printJob = new PrintJob();
		
		printJob.setPrintOrder(printOrder);
		
		System.out.println("\n\n print jobOrder:"+printOrder.getOrderID());
		
		model.addAttribute(printJob);
		model.addAttribute(printOrder);

		return "printJobForm";
	}

	@RequestMapping(value ="/printJobAgain.htm", method = RequestMethod.GET)
	public String printJobAgain(
			@ModelAttribute("printOrder") PrintOrder printOrder, Model model,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		printOrder = (PrintOrder)session.getAttribute("orderSession");
		if(printOrder == null)
		{
			System.out.println("\n\n\n\n\n\n\n\nHELLO");
		}
		
		//session.setAttribute("orderSession", printOrder);
		model.addAttribute("printOrder", printOrder);
		return "redirect:/newPrintJob.htm";
	}
	
	
	
}
