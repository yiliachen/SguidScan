package com.example.service;

import com.example.model.EntityObject;

public interface EOService {
	Iterable<EntityObject> getAllEntityObjects();
	EntityObject getEntityObjectById(long id);
	Iterable<EntityObject> getEntityByName(String __name);
	void remove(EntityObject __entity);
	void save(EntityObject __entity);
	Iterable<EntityObject> getEntityByPath(String __path);
}
