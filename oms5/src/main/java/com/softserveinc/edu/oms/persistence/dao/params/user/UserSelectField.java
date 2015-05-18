package com.softserveinc.edu.oms.persistence.dao.params.user;

public enum UserSelectField {
	ALL("All Columns"), FIRST_NAME("First Name"), LAST_NAME("Last Name"), LOGIN("Login"), ROLE("Role"), REGION("Region");

	private final String displayText;

	private UserSelectField(final String displayText) {
		this.displayText = displayText;
	}

	public String getDisplayText() {
		return displayText;
	}

	@Override
	public String toString() {
		return displayText;
	}
}
