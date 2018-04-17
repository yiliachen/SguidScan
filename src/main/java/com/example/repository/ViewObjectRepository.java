package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ViewObject;
@Repository("viewobjectRepository")
public interface ViewObjectRepository extends CrudRepository<ViewObject, Long>{
	Iterable<ViewObject> findByName(String Name);
	Iterable<ViewObject> findByPath(String Path);
}
