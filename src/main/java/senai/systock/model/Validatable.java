package senai.systock.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

public class Validatable {

	public Set<ConstraintViolation<Object>> validar() {
		return Validation.buildDefaultValidatorFactory().getValidator().validate(this);
	}
	
}
