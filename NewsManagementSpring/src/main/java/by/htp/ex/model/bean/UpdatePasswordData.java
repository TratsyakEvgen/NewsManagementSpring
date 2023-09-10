package by.htp.ex.model.bean;

import java.io.Serializable;

import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.constant.RegExConst;
import by.htp.ex.util.validation.annatation.Equal;
import by.htp.ex.util.validation.annatation.RegEx;
import lombok.Data;

@Data
public class UpdatePasswordData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@RegEx(regex = RegExConst.PASSWORD, message = ErrorCode.PASSWORD_VALIDATION)
	@Equal(field = "repeatPassword", message = ErrorCode.MATH_PASSWORDS_VALIDATION)
	private String password;

	private String repeatPassword;

}
