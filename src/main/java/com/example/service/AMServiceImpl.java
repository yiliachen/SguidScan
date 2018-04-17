package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.AM;
import com.example.repository.AMRepository;

@Service("amService")
public class AMServiceImpl implements AMService {
	
	@Autowired
	AMRepository amrepository;

	@Override
	public Iterable<AM> getAllAMs() {
		return amrepository.findAll();
	}

	@Override
	public AM getAMById(long id) {
		return amrepository.findOne(id);
	}

	@Override
	public void add(AM __am) {
		amrepository.save(__am);
	}

	@Override
	public void deleteall() {
		amrepository.deleteAll();
	}

	public Iterable<AM> findByName(String name) {
		return amrepository.findByName(name);
	}

	public void delete(AM __am){
		 amrepository.delete(__am);
	}

	@Override
	public Iterable<AM> findByPath(String path) {
		return amrepository.findByPath(path);
	}
}
