package com.example.service;

import com.example.model.AM;

public interface AMService {
	public Iterable<AM> getAllAMs();
	public AM getAMById(long id);
	public void add(AM __am);
	public void deleteall();
	public Iterable<AM> findByName(String name);
	public void delete(AM __am);
	public Iterable<AM> findByPath(String Path);
}
