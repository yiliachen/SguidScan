package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.SDF;
import com.example.repository.SDFRepository;

@Service("sdfService")
public class SDFServiceImpl implements SDFService {

	@Autowired
	SDFRepository sdfrepository;
	
	@Override
	public Iterable<SDF> getAllSDFs() {
		return sdfrepository.findAll();
	}

	@Override
	public SDF getSDFById(long id) {
		return sdfrepository.findOne(id);
	}

	@Override
	public void remove(SDF __sdf) {
		sdfrepository.delete(__sdf);
	}

	@Override
	public Iterable<SDF> finbyPath(String __name) {
		return sdfrepository.findByPath(__name);
	}

	@Override
	public void deleteAll() {
		sdfrepository.deleteAll();
	}

	@Override
	public void save(SDF __sdf) {
		sdfrepository.save(__sdf);
	}
}
