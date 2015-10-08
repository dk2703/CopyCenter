package com.copycenter.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

@Entity
public class PrintOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3733815995515398938L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderID;
	private String numberOfJobs;

	private Date dateSent;

	@Field(index = Index.TOKENIZED, store = Store.YES)
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Entity Mappings
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "printOrder")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PrintJob> printJobList = new ArrayList<PrintJob>();

	@ManyToOne
	@JoinColumn(name = "student_id")
	@ContainedIn
	private Student student;

	@ManyToOne
	@JoinColumn(name = "storeManager_id")
	private StoreManager storeManager;

	public String getNumberOfJobs() {
		return numberOfJobs;
	}

	public void setNumberOfJobs(String numberOfJobs) {
		this.numberOfJobs = numberOfJobs;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public List<PrintJob> getPrintJobList() {
		return printJobList;
	}

	public void setPrintJobList(List<PrintJob> printJobList) {
		this.printJobList = printJobList;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StoreManager getStoreManager() {
		return storeManager;
	}

	public void setStoreManager(StoreManager storeManager) {
		this.storeManager = storeManager;
	}

}
