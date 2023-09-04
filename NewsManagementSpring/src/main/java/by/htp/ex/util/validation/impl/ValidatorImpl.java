package by.htp.ex.util.validation.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import by.htp.ex.util.ErrorCode;
import by.htp.ex.util.validation.AnnatationHandler;
import by.htp.ex.util.validation.Validator;
import by.htp.ex.util.validation.annatation.Equal;
import by.htp.ex.util.validation.annatation.RegEx;
import jakarta.xml.bind.ValidationException;

@Component
public class ValidatorImpl implements Validator {

	private Map<Class<? extends Annotation>, AnnatationHandler> rules = new HashMap<>();

	public ValidatorImpl() {
		rules.put(RegEx.class, new RegExHandler());
		rules.put(Equal.class, new EqualHandler());
	}

	@Override
	public <T> Optional<List<ErrorCode>> check(T object) throws ValidationException {
		List<ErrorCode> codes = null;
		for (Field f : object.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			for (Annotation ann : f.getAnnotations()) {
				Class<? extends Annotation> clazz = ann.annotationType();
				if (rules.containsKey(clazz)) {
					codes = rules.get(clazz).check(codes, f, object);

				}
			}
		}
		return Optional.ofNullable(codes);
	}

}
