package by.htp.ex.model.bean;

import java.io.Serializable;

import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.constant.RegExConst;
import by.htp.ex.util.validation.annatation.Equal;
import by.htp.ex.util.validation.annatation.RegEx;
import lombok.Data;

@Data
public class RegistrationData implements Serializable {
	private static final long serialVersionUID = 1L;

	@RegEx(regex = RegExConst.NAME, message = ErrorCode.NAME_VALIDATION)
	private String name;

	@RegEx(regex = RegExConst.NAME, message = ErrorCode.SURNAME_VALIDATION)
	private String surname;

	@RegEx(regex = RegExConst.EMAIL, message = ErrorCode.EMAIL_VALIDATION)
	private String email;

	@RegEx(regex = RegExConst.LOGIN, message = ErrorCode.LOGIN_VALIDATION)
	private String username;

	@RegEx(regex = RegExConst.PASSWORD, message = ErrorCode.PASSWORD_VALIDATION)
	@Equal(field = "repeatPassword", message = ErrorCode.MATH_PASSWORDS_VALIDATION)
	private String password;

	private String repeatPassword;

}
