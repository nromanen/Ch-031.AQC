package com.softserveinc.edu.oms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.oms.domain.entities.CustomerType;
import com.softserveinc.edu.oms.persistence.dao.interfaces.ICustomerTypeDao;
import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;
import com.softserveinc.edu.oms.service.interfaces.ICustomerTypeService;

@Service
public class CustomerTypeService implements ICustomerTypeService{
	
	private ICustomerTypeDao customerTypeDao;
	
	@Autowired
	public void setCustomerTypeDao(ICustomerTypeDao customerTypeDao) {
		this.customerTypeDao = customerTypeDao;
	}

	@Transactional
	@Override
	public CustomerType getStandartTypeDiscount() {
		return customerTypeDao.getStandartTypeDiscount();
	}
	
	@Transactional
	@Override
	public CustomerType getSilverTypeDiscount() {
		return customerTypeDao.getSilverTypeDiscount();
	}

	@Transactional
	@Override
	public CustomerType getGoldTypeDiscount() {
		return customerTypeDao.getGoldTypeDiscount();
	}

	@Transactional
	@Override
	public CustomerType getPlatinumTypeDiscount() {
		return customerTypeDao.getPlatinumTypeDiscount();
	}

	@Transactional
	@Override
	public Long getRowCount() {
		return customerTypeDao.getRowCount();
	}

	@Transactional
	@Override
	public List<CustomerType> findAll() {
		return customerTypeDao.findAll();
	}

	@Transactional
	@Override
	public List<CustomerType> findAll(SortProperties sortProperties) {
		return customerTypeDao.findAll(sortProperties);
	}

	@Transactional
	@Override
	public CustomerType findByID(Integer id) {
		return customerTypeDao.findByID(id);
	}

	@Transactional
	@Override
	public CustomerType insertOrUpdate(CustomerType entity) {
		return customerTypeDao.insertOrUpdate(entity);
	}

	@Transactional
	@Override
	public void delete(CustomerType entity) {
		customerTypeDao.delete(entity);
	}

}
