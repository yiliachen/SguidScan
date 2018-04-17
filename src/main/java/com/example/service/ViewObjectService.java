package com.example.service;

import com.example.model.ViewObject;

public interface ViewObjectService {
	Iterable<ViewObject> getAllViewObjects();
	ViewObject getViewObjectById(long id);
	void delete(ViewObject __vo);
	Iterable<ViewObject> getViewObjectByName(String __name);
	Iterable<ViewObject> getViewObjectByPath(String __path);
	void save(ViewObject __vo);
}
