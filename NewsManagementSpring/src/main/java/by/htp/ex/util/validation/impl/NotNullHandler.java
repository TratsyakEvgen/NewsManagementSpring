package by.htp.ex.util.validation.impl;

import java.lang.reflect.Field;
import java.util.List;

import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.validation.AbstractAnnatationHandler;
import by.htp.ex.util.validation.annatation.NotNull;
import jakarta.xml.bind.ValidationException;

public class NotNullHandler extends AbstractAnnatationHandler{

	@Override
	public <T> List<ErrorCode> check(List<ErrorCode> codes, Field field, T object) throws ValidationException {
		NotNull annatation = field.getAnnotation(NotNull.class);		
		try {	
			if (field.get(object) == null || field.get(object).toString().isEmpty()) {
				codes = addMessage(codes, annatation.message());
			}
		} catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
			throw new ValidationException("Can't get field value", e);
		}
		return codes;
	}

}
