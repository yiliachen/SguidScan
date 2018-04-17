package com.example.service;

import com.example.model.DBObject;

public interface DBObjectService {
	Iterable<DBObject> getAllDBObjects();
	DBObject getDBObjectById(long id);
	Iterable<DBObject> getDBObjectByName(String name);
	void remove(DBObject dbobj);
	void save(DBObject __dbobj);
	Iterable<DBObject> getDBObjectByPath(String path);
}
