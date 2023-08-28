package by.htp.ex.util;



public enum ErrorCode {

	NEWS_NOT_FOUND("local.news.not.found"),
	FILE_DOES_NOT_EXIST("local.file.does.not.exit"),
	FILE_NOT_SELECTED("local.file.not.selected"),
	FILE_ALREADY_EXIST("local.file.already.exit"),
	IMAGE_ALREADY_EXIST("local.image.already.exists"),
	FILES_NOT_FOUND("local.files.not.found"),
	IMAGES_NOT_FOUND("local.images.not.found"),
	INTERAL_SERVER_ERROR("local.internal.server.error");

	private String title;

	private ErrorCode(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
