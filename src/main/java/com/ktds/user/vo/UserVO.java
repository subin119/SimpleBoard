package com.ktds.user.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class UserVO {
	
	@NotEmpty(message="필수값입니다!")
	@NotNull(message="필수값입니다!")
	private String userId;
	@NotEmpty(message="필수값입니다!")
	@NotNull(message="필수값입니다!")
	private String userHashedPassword;
	private String userSalt;
	private String userNickName;
	private String createdDate;
	private int point;
	
	/**
	 * 로그인 페이지에서 "아이디 기억하기" 체크박스의 값을 저장한다.
	 */
	private boolean rememberUserId;
	
	/**
	 * jstl에서는 isRememberUserId를 읽지 못하기 때문에 el을 사용하기 위해서 getter를 추가해준다.
	 * @return
	 */
	/*public boolean getRememberUserId() {
		return rememberUserId;
	}*/
	
	public boolean isRememberUserId() {
		return rememberUserId;
	}
	public void setRememberUserId(boolean rememberUserId) {
		this.rememberUserId = rememberUserId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserHashedPassword() {
		return userHashedPassword;
	}
	public void setUserHashedPassword(String userHashedPassword) {
		this.userHashedPassword = userHashedPassword;
	}
	public String getUserSalt() {
		return userSalt;
	}
	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
}	
