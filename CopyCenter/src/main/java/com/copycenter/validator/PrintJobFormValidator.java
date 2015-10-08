package com.copycenter.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.copycenter.pojo.PrintJob;

public class PrintJobFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {

		PrintJob pj = (PrintJob) target;

		if (pj.getDueDate().equals("") || pj.getDueDate().equals("mm/dd/yyy")) {
			errors.rejectValue("dueDate", "dueDate.notSelect");
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(pj.getDueDate());
				Date CurrentDate = new Date();
				String currentDatef = sdf.format(CurrentDate);
				Date date2 = sdf.parse(currentDatef);

				System.out.println(date);

				if (date.before(date2)) {
					errors.rejectValue("dueDate", "dueDate.Wrong");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (pj.getPageSize().contains(",")) {
			String replaced=pj.getPageSize().replace(",", "");

			if (replaced.equals("") && replaced.contains("[x]")!=true)
			{
				errors.rejectValue("pageSize", "pageSize.NotSelected");
			}
		}
		
		if (pj.getQuantity() == null) {
			errors.rejectValue("quantity", "quantity.Invalid");
		} else if (pj.getQuantity() <= 0) {
			errors.rejectValue("quantity", "quantity.Invalid");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobPriority",
				"jobPriority.NotSelected");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobType",
				"jobType.NotSelected");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "printType",
				"printType.NotSelected");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pageSize",
				"pageSize.NotSelected");

	}

	public void customValidator(CommonsMultipartFile cm, Errors errors) {
		if (cm.getSize() == 0) {
			errors.rejectValue("filename", "file.NotSelected");
		}
	}

}
