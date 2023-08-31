package by.htp.ex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.htp.ex.util.ErrorCode;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<ErrorCode> listCode;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(ErrorCode code) {
		listCode = new ArrayList<>();
		listCode.add(code);
	}
	
	public ServiceException(List<ErrorCode> codes) {
		listCode = new ArrayList<>(codes);
	}

	public Optional<List<ErrorCode>> getCodes() {
		return Optional.ofNullable(listCode);
	}

}
