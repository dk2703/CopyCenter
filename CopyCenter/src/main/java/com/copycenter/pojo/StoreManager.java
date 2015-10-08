package com.copycenter.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class StoreManager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer storeManagerID;

	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;

	// Entity Mapping

	@OneToOne
	private UserAccount userAccount;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "storeManager")
	private List<PrintOrder> printOrder;

	public Integer getStoreManagerID() {
		return storeManagerID;
	}

	public void setStoreManagerID(Integer storeManagerID) {
		this.storeManagerID = storeManagerID;
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

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<PrintOrder> getPrintOrder() {
		return printOrder;
	}

	public void setPrintOrder(List<PrintOrder> printOrder) {
		this.printOrder = printOrder;
	}

}
