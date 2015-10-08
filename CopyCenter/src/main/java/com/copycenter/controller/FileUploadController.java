package com.copycenter.controller;

import java.io.File;
import java.util.Locale;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.copycenter.DAO.PrintJobDAO;
import com.copycenter.DAO.PrintOrderDAO;
import com.copycenter.DAO.StudentDAO;
import com.copycenter.pojo.PrintJob;
import com.copycenter.pojo.PrintOrder;
import com.copycenter.pojo.Student;
import com.copycenter.pojo.UserAccount;
import com.copycenter.validator.PrintJobFormValidator;

@Controller
public class FileUploadController {

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private PrintOrderDAO printOrderDAO;

	@Autowired
	private PrintJobDAO printJobDAO;

	@Autowired
	private PrintJobFormValidator printJobFormValidator;

	@RequestMapping(value = "/uploadFile.htm", method = RequestMethod.POST)
	public String fileUpload(@ModelAttribute("printJob") PrintJob printJob,
			@RequestParam("uploadedFile") CommonsMultipartFile uploadedfile,
			HttpServletRequest request, Model model, Locale locale,
			BindingResult br, BindingResult br2) throws Exception {
		String ps = printJob.getPageSize();
		if (ps.contains(",")) {
			ps.replace(",", "");
			printJob.setPageSize(ps);
		}

		printJobFormValidator.validate(printJob, br);
		printJobFormValidator.customValidator(uploadedfile, br2);

		if (br.hasErrors() || (br2.hasErrors())) {
			printJob = new PrintJob();

			return "printJobForm";
		}

		String format = "";
		HttpSession session = request.getSession();

		PrintOrder printOrder = (PrintOrder) session
				.getAttribute("orderSession");

		if (session.getAttribute("student") == null
				&& (session.getAttribute("storeManger") == null)) {
			return "error";
		}

		UserAccount account = (UserAccount) session
				.getAttribute("storeManager");

		if (account != null) {
			return "error";
		}

		UserAccount account2 = (UserAccount) session.getAttribute("student");

		Student student = studentDAO.getStudent(account2);

		String fileName = (student.getFirstName() + "_" + uploadedfile
				.getOriginalFilename());

		System.out.println("\n\nfilename:   " + fileName);

		format = uploadedfile.getContentType();

		System.out.println("format:" + format + "\n\n");

		if ((format.contains("pdf")) || (format.contains("doc"))
				|| (format.contains("png")) || (format.contains("jpg"))
				|| (format.contains("jpeg"))||(format.contains("docx"))) {

			String path = "/Users/dipesh/Documents/workspace/khandpekar_dipesh_project/src/main/webapp/uploads/";

			File f = new File(path + fileName);
			System.out.println("filef:  " + f.toString());
			uploadedfile.transferTo(new File(path + fileName));

			String uploadDirectory = "/uploads/";
			String finalpath = session.getServletContext().getRealPath(
					uploadDirectory);

			System.out.println(finalpath);
			model.addAttribute(finalpath);

			printJob.setFilename(fileName);
			printJob.setPath(path);
			printJob.setPrintOrder(printOrder);

			printJobDAO.saveJob(printJob);

			return "uploadSuccess";
		}
		return "uploadFailure";
	}

}
