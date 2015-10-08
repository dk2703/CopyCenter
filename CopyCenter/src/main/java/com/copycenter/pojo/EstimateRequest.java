package com.copycenter.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EstimateRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer estimateID;

	private String topic;
	private String description;

	private String user;

	private String fromUser;

	private String sentDate;

	public String getSentDate() {
		return sentDate;
	}

	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}

	public Integer getEstimateID() {
		return estimateID;
	}

	public void setEstimateID(Integer estimateID) {
		this.estimateID = estimateID;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

}
