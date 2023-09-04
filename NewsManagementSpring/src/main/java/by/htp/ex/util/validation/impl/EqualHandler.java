package by.htp.ex.util.validation.impl;

import java.lang.reflect.Field;
import java.util.List;

import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.validation.annatation.Equal;
import jakarta.xml.bind.ValidationException;

public class EqualHandler extends AnnatationHandlerImpl {

	@Override
	public <T> List<ErrorCode> check(List<ErrorCode> codes, Field field, T object) throws ValidationException {
		Equal annatation = field.getAnnotation(Equal.class);

		String fieldName = annatation.field();
		Field checkField = null;
		try {
			checkField = object.getClass().getDeclaredField(fieldName);
			checkField.setAccessible(true);
			Object checkObject = checkField.get(object);
			if (!field.get(object).equals(checkObject)) {
				codes = addMessage(codes, annatation.message());
			}
		} catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
			throw new ValidationException("Can't get field value", e);
		} catch (NoSuchFieldException e) {
			throw new ValidationException("Doesn't have a field " + fieldName, e);
		}

		return codes;
	}

}
