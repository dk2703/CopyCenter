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

@Controller
public class CancelController {
	
	@Autowired
	private PrintOrderDAO printOrderDAO;
	
	@RequestMapping(value="/cancelOrder.htm",method=RequestMethod.GET)
	public String cancelController(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession(false);

		if (session.getAttribute("student") == null
				&& session.getAttribute("storeManager") == null)
		{
			return "invalid";
		}
		
		if(session.getAttribute("student")!= null)
		{
			PrintOrder po=(PrintOrder)session.getAttribute("orderSession");
			
			PrintOrder po2 = printOrderDAO.getOrdersByOrderID(po.getOrderID());
			
			if(po2.getPrintJobList().size()==0)
			{
				printOrderDAO.deleteOrderAtOrderID(po2.getOrderID());
			}
		}
		
		model.addAttribute("student",session.getAttribute("student"));
		return "redirect:/studentHome.htm";
	}

}
