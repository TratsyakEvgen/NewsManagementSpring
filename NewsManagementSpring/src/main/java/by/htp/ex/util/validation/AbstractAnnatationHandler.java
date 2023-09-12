package by.htp.ex.util.validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.util.ErrorCode;
import jakarta.xml.bind.ValidationException;

public abstract class AbstractAnnatationHandler implements AnnatationHandler{
	
	@Override
	public abstract <T> List<ErrorCode> check(List<ErrorCode> codes, Field field, T object) throws ValidationException;
	
	@Override
	public List<ErrorCode> addMessage(List<ErrorCode> codes, ErrorCode code) {
		if (codes == null) {
			codes = new ArrayList<>();
		}
		codes.add(code);
		return codes;
	}

}
