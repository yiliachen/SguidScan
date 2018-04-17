package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.SDF;

@Repository("sdfRepository")
public interface SDFRepository extends CrudRepository<SDF, Long>{
	Iterable<SDF> findByPath(String Path);
}
