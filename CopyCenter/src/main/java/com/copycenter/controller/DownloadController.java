package com.copycenter.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.copycenter.DAO.PrintJobDAO;
import com.copycenter.pojo.PrintJob;

@Controller
public class DownloadController {

	@Autowired
	private PrintJobDAO printJobDAO;

	@RequestMapping(value = "/downloadfile.htm")
	public void handelDownload(HttpServletResponse response,
			@RequestParam("download") int printJobID) {

		OutputStream outputStream = null;
		InputStream is = null;

		PrintJob p = printJobDAO.getSinglePrintJobByID(printJobID);

		try {

			System.out.println(p.getPath() + p.getFilename());
			is = new FileInputStream(p.getPath() + p.getFilename());

			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ p.getFilename() + "\"");
			outputStream = response.getOutputStream();
			while (0 < (bytesRead = is.read(buffer))) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
