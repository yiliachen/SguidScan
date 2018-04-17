package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.EntityObject;

@Repository("eoRepository")
public interface EORepository extends CrudRepository<EntityObject, Long>{
	Iterable<EntityObject> findByName(String Name);
	Iterable<EntityObject> findByPath(String Path);
}
