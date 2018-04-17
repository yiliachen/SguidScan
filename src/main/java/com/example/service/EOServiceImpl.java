package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.EntityObject;
import com.example.repository.EORepository;

@Service("eoService")
public class EOServiceImpl implements EOService {

	@Autowired
	EORepository eorepository;
	
	@Override
	public Iterable<EntityObject> getAllEntityObjects() {
		return eorepository.findAll();
	}

	@Override
	public EntityObject getEntityObjectById(long id) {
		return eorepository.findOne(id);
	}

	@Override
	public Iterable<EntityObject> getEntityByName(String __name) {
		return eorepository.findByName(__name);
	}

	@Override
	public void remove(EntityObject __entity) {
		eorepository.delete(__entity);
	}

	@Override
	public void save(EntityObject __entity) {
		eorepository.save(__entity);
	}

	@Override
	public Iterable<EntityObject> getEntityByPath(String __path) {
		return eorepository.findByPath(__path);
	}
	
	
}
