package com.kronsoft.internship.entities;

public enum AppointmentStatus {

	CREATED(0),

	FINISHED(1),

	CANCELLED(2);

	private Integer key;

	private AppointmentStatus(Integer key) {
		this.key = key;
	}

	public Integer getKey() {
		return key;
	}
	
}
