package by.htp.ex.util.validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.validation.annatation.Equal;
import by.htp.ex.util.validation.annatation.RegEx;
import jakarta.xml.bind.ValidationException;

public class Validator {

	public static <T> List<ErrorCode> check(T object) throws ValidationException {
		List<ErrorCode> codes = null;
		for (Field f : object.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if (f.isAnnotationPresent(RegEx.class)) {
				codes = checkRegEx(codes, f, object);
			}
			if (f.isAnnotationPresent(Equal.class)) {
				codes = checkEqual(codes, f, object);
			}
		}
		return codes;
	}

	private static <T> List<ErrorCode> checkRegEx(List<ErrorCode> codes, Field field, T object)
			throws ValidationException {
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

	private static <T> List<ErrorCode> checkEqual(List<ErrorCode> codes, Field field, T object)
			throws ValidationException {
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

	private static List<ErrorCode> addMessage(List<ErrorCode> codes, ErrorCode code) {
		if (codes == null) {
			codes = new ArrayList<>();
		}
		codes.add(code);
		return codes;
	}

}
