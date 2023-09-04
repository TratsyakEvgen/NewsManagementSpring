package by.htp.ex.util.validation.impl;

import java.lang.reflect.Field;
import java.util.List;

import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.validation.annatation.RegEx;
import jakarta.xml.bind.ValidationException;

public class RegExHandler extends AnnatationHandlerImpl{

	@Override
	public <T> List<ErrorCode> check(List<ErrorCode> codes, Field field, T object) throws ValidationException {
		RegEx annatation = field.getAnnotation(RegEx.class);		
		try {
			
			if (!field.get(object).toString().matches(annatation.regex())) {
				codes = addMessage(codes, annatation.message());
			}
		} catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
			throw new ValidationException("Can't get field value", e);
		}

		return codes;
	}

}
