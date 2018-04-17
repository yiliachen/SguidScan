package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.DBObject;
import com.example.repository.DBObjectRepository;

@Service("dbobjectService")
public class DBObjectServiceImpl implements DBObjectService {

	@Autowired
	DBObjectRepository dbobjectrepository;
	
	@Override
	public Iterable<DBObject> getAllDBObjects() {
		return dbobjectrepository.findAll();
	}

	@Override
	public DBObject getDBObjectById(long id) {
		return dbobjectrepository.findOne(id);
	}

	@Override
	public Iterable<DBObject> getDBObjectByName(String name) {
		return dbobjectrepository.findByName(name);
	}

	@Override
	public void remove(DBObject dbobj) {
		dbobjectrepository.delete(dbobj);
	}

	@Override
	public void save(DBObject __dbobj) {
		dbobjectrepository.save(__dbobj);
	}

	@Override
	public Iterable<DBObject> getDBObjectByPath(String path) {
		return dbobjectrepository.findByPath(path);
	}
}
