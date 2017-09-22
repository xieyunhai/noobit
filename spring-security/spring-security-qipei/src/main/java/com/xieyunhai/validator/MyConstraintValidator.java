package com.xieyunhai.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author admin
 * @since 2017/9/22 10:47
 */
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
	@Override
	public void initialize(MyConstraint myConstraint) {
		log.info("my constraint init");
	}

	@Override
	public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
		return false;
	}
}
