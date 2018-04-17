package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AM;

@Repository
public interface AMRepository extends CrudRepository<AM, Long>{
	Iterable<AM> findByName(String name);
	Iterable<AM> findByPath(String path);
}