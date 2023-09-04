package by.htp.ex.util.validation;

import java.lang.reflect.Field;
import java.util.List;

import by.htp.ex.util.ErrorCode;
import jakarta.xml.bind.ValidationException;

public interface AnnatationHandler {
	
	<T> List<ErrorCode> check(List<ErrorCode> codes, Field field, T object) throws ValidationException;
	
	List<ErrorCode> addMessage(List<ErrorCode> codes, ErrorCode code);

}
