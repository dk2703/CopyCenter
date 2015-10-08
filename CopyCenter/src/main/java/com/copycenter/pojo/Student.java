package com.copycenter.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;



@Entity
@Indexed
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5371204107695282225L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	private Integer studentID;
	
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String firstName;
	
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String lastName;
	
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String contactNumber;
	
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String email;

	// Entitiy Mapping
	@OneToOne(cascade=CascadeType.ALL)
	private UserAccount userAccount;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch=FetchType.EAGER)
	@IndexedEmbedded
	private List<PrintOrder> printOrdersList = new ArrayList<PrintOrder>();

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public List<PrintOrder> getPrintOrdersList() {
		return printOrdersList;
	}

	public void setPrintOrdersList(List<PrintOrder> printOrdersList) {
		this.printOrdersList = printOrdersList;
	}
}
