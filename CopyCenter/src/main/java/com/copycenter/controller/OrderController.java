package com.copycenter.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.copycenter.DAO.PrintOrderDAO;
import com.copycenter.DAO.StudentDAO;
import com.copycenter.pojo.PrintOrder;
import com.copycenter.pojo.Student;
import com.copycenter.pojo.UserAccount;

@Controller
public class OrderController {

	@Autowired
	private PrintOrderDAO printOrderDAO;

	@Autowired
	private StudentDAO studentDAO;

	@RequestMapping(value = "/newOrder.htm", method = RequestMethod.GET)
	public String createNewOrder(Model model, HttpServletRequest request)throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String formattedDate = dateFormat.format(date);
		Date date2 = dateFormat.parse(formattedDate);

		HttpSession session = request.getSession();

		if (session.getAttribute("student") == null
				&& (session.getAttribute("storeManger") == null)) {
			return "error";
		}

		UserAccount account = (UserAccount) session.getAttribute("student");

		if (account == null) {
			return "error";
		}

		Student s = studentDAO.getStudent(account);

		PrintOrder printOrder = new PrintOrder();
		
		printOrder.setStudent(s);
		printOrder.setDateSent(date2);
		printOrder.setStatus("Pending");

		printOrderDAO.savePrintOrder(printOrder);
		//ArrayList<Student> student =  studentDAO.searchByString("dipesh");		
		
		//if(student!= null)
		//{
			//System.out.println("\n\n\n\n\n\nNot Null\n\n\n\n\n");
		//}/
		model.addAttribute("printOrder", printOrder);
		session.setAttribute("orderSession", printOrder);

		return "orderForm";
	}
}
