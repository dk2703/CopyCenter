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

import com.copycenter.DAO.PrintJobDAO;
import com.copycenter.pojo.PrintJob;
import com.copycenter.pojo.UserAccount;

@Controller
public class ViewJobController {

	@Autowired
	private PrintJobDAO printJobDAO;

	@RequestMapping(value = "/view.htm", method = RequestMethod.GET)
	public String viewJob(@RequestParam("print") int orderid, Model model,
			HttpServletRequest request) {

		System.out.println("\n\n" + orderid + "\n\n");
		ArrayList<PrintJob> pjlist = null;

		pjlist = printJobDAO.getPrintJobByID(orderid);

		model.addAttribute("printJobList", pjlist);

		return "viewJob";
	}

	@RequestMapping(value = "/viewJobStudent.htm", method = RequestMethod.GET)
	public String viewJobStudent(@RequestParam("pid") int orderid, Model model,
			HttpServletRequest request) {

		System.out.println("\n\n" + orderid + "\n\n");
		ArrayList<PrintJob> pjlist = null;

		pjlist = printJobDAO.getPrintJobByID(orderid);

		model.addAttribute("printJobList", pjlist);

		return "viewJobsStudent";
	}

	@RequestMapping(value = "/cancelJob.htm", method = RequestMethod.GET)
	public String deleteJob(@RequestParam("cancel") int jobid, Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session.getAttribute("student") == null
				&& (session.getAttribute("storeManger") == null)) {
			return "error";
		}

		UserAccount account = (UserAccount) session
				.getAttribute("storeManager");

		if (account != null) {
			return "unAuthorized";
		}

		printJobDAO.deleteJobBy(jobid);

		return "jobCancelled";

	}

}
