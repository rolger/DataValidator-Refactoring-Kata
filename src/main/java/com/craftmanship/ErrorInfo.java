package com.craftmanship;

public class ErrorInfo {
	private Integer row;
	private String detailMessage;

	public ErrorInfo(Integer row, String detailMessage) {
		super();
		this.row = row;
		this.detailMessage = detailMessage;
	}

	public String message() {
		return String.format("Invalid data in row %d (%s)", row, detailMessage);
	}
}