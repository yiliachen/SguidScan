package com.example.service;
import org.springframework.data.domain.Page;

import com.example.model.BusinessObjects;

public interface BusinessObjectsService {

	public void create(BusinessObjects entity);

	public void update(BusinessObjects entity);

	public BusinessObjects getByPrimaryKey(Integer key);

	public void deleteByPrimaryKey(Integer key);

	public Page<BusinessObjects> getPagerList(Integer pageIndex, Integer pageSize);

	public Page<BusinessObjects> getPagerList(Integer pageIndex, Integer pageSize, String sortColumn, String sortType);

	public Page<BusinessObjects> getPagerListByCondition(Integer pageIndex, Integer pageSize, final BusinessObjects entity);

	public Page<BusinessObjects> getPagerListByCondition(Integer pageIndex, Integer pageSize, String sortColumn, String sortType, final BusinessObjects entity);

	public long getTotalRecordCount();
	
	public Iterable<BusinessObjects> getByEqualSpec(final BusinessObjects bo);

}
