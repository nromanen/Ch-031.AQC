package com.softserveinc.edu.oms.web.itemManagement;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.edu.oms.web.itemManagement.model.ProductModel;

public class ProductValidator implements Validator{

	private static final String STRING_DOUBLE_REGEXP = "[0-9]{1,13}(\\.[0-9]*)?";
	private static final int MIN_PRODUCT_NAME_LENGTH = 3;
	private static final int MAX_PRODUCT_NAME_LENGTH = 13;
	private static final int MAX_PRODUCT_DESCRIPTION = 25;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ProductModel.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "productPrice", "if.pPriceEnter");
		ProductModel productModel = (ProductModel) object;
		if (productModel.getProductName() == null
			|| productModel.getProductName().trim().length() == 0) {
		    errors.rejectValue("productName", "if.pNameEnter");
		} else if (productModel.getProductName().length() < MIN_PRODUCT_NAME_LENGTH
			|| productModel.getProductName().length() > MAX_PRODUCT_NAME_LENGTH) {
		    errors.rejectValue("productName", "if.pProductNameWrongFormat");
		}
		if (productModel.getProductDescription().length() > MAX_PRODUCT_DESCRIPTION) {
			errors.rejectValue("productDescription", "if.pProductDescriptionMaxLength");
		}
		if (productModel.getProductPrice() != null && productModel.getProductPrice().length() > 0) {
		    if (!productModel.getProductPrice().matches(STRING_DOUBLE_REGEXP)) {
			errors.rejectValue("productPrice", "if.pPriceWrongFormat");
		    }else if (Double.valueOf(productModel.getProductPrice()) < 0 ){
				errors.rejectValue("productPrice", "if.pPriceNegativeEnter");
		    }
		}
	}

}
