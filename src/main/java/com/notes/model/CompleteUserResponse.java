package com.notes.model;

public class CompleteUserResponse {
	private boolean success;
	private int status;
	private String message;
	private UserProfileDetails data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserProfileDetails getData() {
		return data;
	}

	public void setData(UserProfileDetails data) {
		this.data = data;
	}
	/*
	 * @Override public String toString() { return "FullUserProfileResponse{" +
	 * "success=" + success + ", status=" + status + ", message='" + message + '\''
	 * + ", data=" + data + '}'; }
	 */
}
