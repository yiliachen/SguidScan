package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.DBObject;;
@Repository("dbobjectRepository")
public interface DBObjectRepository extends CrudRepository<DBObject, Long>{
	Iterable<DBObject> findByName(String Name);
	Iterable<DBObject> findByPath(String path);
}
