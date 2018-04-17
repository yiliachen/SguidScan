package com.example.service;

import com.example.model.SDF;

public interface SDFService {
	public Iterable<SDF> getAllSDFs();
	public SDF getSDFById(long id);
	public void remove(SDF __sdf);
	public Iterable<SDF> finbyPath(String __path);
	public void deleteAll();
	public void save(SDF __sdf);
}
