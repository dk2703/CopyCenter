package com.copycenter.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.copycenter.DAO.SearchDAO;
import com.copycenter.pojo.Student;

@Controller
public class SearchController {

	@Autowired
	private SearchDAO searchDAO;

	@RequestMapping(value = "/searchOrder.htm", method = RequestMethod.POST)
	public String search(@RequestParam("keyword") String keyword, Model model,
			HttpServletRequest request) {

		ArrayList<Student> list = searchDAO.searchStudent(keyword);

		if (list == null) {

			return "keywordNotFound";
		}

		model.addAttribute("searchedPrintOrder", list);

		return "searchResult";
	}
}
