package by.htp.ex.util;



public enum ErrorCode {

	NEWS_NOT_FOUND("local.news.not.found"),
	FILE_DOES_NOT_EXIST("local.file.does.not.exit"),
	FILE_NOT_SELECTED("local.file.not.selected"),
	FILE_ALREADY_EXIST("local.file.already.exit"),
	IMAGE_ALREADY_EXIST("local.image.already.exists"),
	FILES_NOT_FOUND("local.files.not.found"),
	IMAGES_NOT_FOUND("local.images.not.found"),
	INTERAL_SERVER_ERROR("local.internal.server.error"),
	INCORRECT_LOGIN_OR_PASSWORD("local.incorrect.login.or.password"),
	LOGIN_VALIDATION("local.login.validation"),
	PASSWORD_VALIDATION("local.password.validation"),
	EMAIL_VALIDATION("local.email.validation"),
	LINK_VALIDATION("local.link.validation"),
	NAME_VALIDATION("local.name.validation"),
	SURNAME_VALIDATION("local.surname.validation"),
	MATH_PASSWORDS_VALIDATION("local.math.passwords.validation");

	private String title;

	private ErrorCode(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
