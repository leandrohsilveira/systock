package senai.systock.util;

import java.util.Comparator;

import javax.validation.ConstraintViolation;

public class ConstraintViolationComparator implements Comparator<ConstraintViolation<?>> {

	@Override
	public int compare(ConstraintViolation<?> o1, ConstraintViolation<?> o2) {
		return o1.getMessage().compareTo(o2.getMessage());
	}

}
