package com.radiantk.command;

// 비밀번호 변경에 사용할 커맨드 객체 
public class ChangePasswordCommand {

	private String currentPassword;
	private String newPassword;
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
