package com.embark.phonebook.model.test;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.embark.phonebook.model.ValidationImpl;

public class ValidationImplTest {

	@Test
	public void testLengthValidation()
	{
		ValidationImpl validator = new ValidationImpl();
		assertFalse(validator.validateLength("ONeyff", 2, 5));		
	}

}