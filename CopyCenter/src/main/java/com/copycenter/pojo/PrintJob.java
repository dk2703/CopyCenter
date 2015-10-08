package com.copycenter.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.ContainedIn;

@Entity
public class PrintJob implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer printJobID;
	private String description;
	private String jobType;
	private String filename;
	private String pageSize;
	private String jobPriority;
	private String dueDate;
	private String path;

	private String printType;
	private Integer quantity;

	public String getPrintType() {
		return printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	// Entity Mapping
	@ManyToOne
	@JoinColumn(name = "printOrder_id")
	@ContainedIn
	private PrintOrder printOrder;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public PrintOrder getPrintOrder() {
		return printOrder;
	}

	public void setPrintOrder(PrintOrder printOrder) {
		this.printOrder = printOrder;
	}

	public Integer getPrintJobID() {
		return printJobID;
	}

	public void setPrintJobID(Integer printJobID) {
		this.printJobID = printJobID;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(String jobPriority) {
		this.jobPriority = jobPriority;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}
