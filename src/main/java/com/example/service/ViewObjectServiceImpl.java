package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.ViewObject;
import com.example.repository.ViewObjectRepository;

@Service("viewobjectService")
public class ViewObjectServiceImpl implements ViewObjectService {

	@Autowired
	ViewObjectRepository viewobjectrepository;
	
	@Override
	public Iterable<ViewObject> getAllViewObjects() {
		return viewobjectrepository.findAll();
	}

	@Override
	public ViewObject getViewObjectById(long id) {
		return viewobjectrepository.findOne(id);
	}

	@Override
	public void delete(ViewObject __vo) {
		viewobjectrepository.delete(__vo);
	}

	@Override
	public Iterable<ViewObject> getViewObjectByName(String __name) {
		return viewobjectrepository.findByName(__name);
	}

	@Override
	public void save(ViewObject __vo) {
		viewobjectrepository.save(__vo);
	}

	@Override
	public Iterable<ViewObject> getViewObjectByPath(String __path) {
		return viewobjectrepository.findByPath(__path);
	}
}
