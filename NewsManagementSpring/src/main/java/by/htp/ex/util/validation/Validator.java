package by.htp.ex.util.validation;

import java.util.List;
import java.util.Optional;

import by.htp.ex.util.ErrorCode;
import jakarta.xml.bind.ValidationException;

public interface Validator {
	
	<T>  Optional<List<ErrorCode>> check(T object) throws ValidationException;

}
