package com.softserveinc.edu.oms.web.product.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ListProductModelValidator implements Validator{
    
    private static final String STRING_INTEGER_REGEXP = "[0-9]{1,4}";
    private static final int MIN_QUANTITY_VALUE = 1;
    private static final int MAX_QUANTITY_VALUE = 1000;

    @Override
    public boolean supports(Class<?> clazz) {
	return ListProductModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	ValidationUtils.rejectIfEmpty(errors, "productId", "liProd.itemSelect");
	
	ListProductModel model = (ListProductModel) target;
	if (model.getQuantity() == null
		|| model.getQuantity().trim().length() == 0) {
	    errors.rejectValue("quantity", "uform.emptyField");
	} else {
	    if (!model.getQuantity().matches(STRING_INTEGER_REGEXP)){
		errors.rejectValue("quantity", "liProd.wrongQuantity");
	    }else if (Integer.valueOf(model.getQuantity()) > MAX_QUANTITY_VALUE
		     || Integer.valueOf(model.getQuantity()) < MIN_QUANTITY_VALUE) {
		errors.rejectValue("quantity", "liProd.wrongQuantity");
	    }	    	    
	}
    }

}
