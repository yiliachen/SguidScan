package com.example.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.model.FileEntrySum;

@Repository
public class FileEntrySumDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(FileEntrySum fe){
		entityManager.persist(fe);
	}
	
	public void update(FileEntrySum fe){
		entityManager.merge(fe);
	}
	
	public FileEntrySum getFileEntrySumById(long id){
		return entityManager.find(FileEntrySum.class, id);
	}
	
	public void delete(long id){
		FileEntrySum fe = getFileEntrySumById(id);
		if(fe != null){
			entityManager.remove(fe);
		}
	}
}
