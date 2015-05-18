package com.softserveinc.edu.oms.web.itemManagement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.softserveinc.edu.oms.domain.entities.Product;
import com.softserveinc.edu.oms.service.interfaces.IProductService;
import com.softserveinc.edu.oms.web.itemManagement.model.ProductModel;

public abstract class AbstractFormProductController {

	@Autowired
	protected IProductService productService;

	@Autowired
	protected ProductValidator productValidator;

	public abstract String prepareForm(final ModelMap modelMap,
			final HttpServletRequest request);

	public abstract String onSubmit(
			@ModelAttribute("userModel") final ProductModel productModel,
			final BindingResult result);

	protected Product createProduct(final ProductModel productModel) {
		Product product = new Product();

		product.setProductName(productModel.getProductName());
		product.setProductDescription(productModel.getProductDescription());
		product.setProductPrice(Double.valueOf(productModel.getProductPrice()));

		return product;
	}
}
