package com.example.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.model.FileEntry;

@Repository
public class FileEntryDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(FileEntry fe){
		entityManager.persist(fe);
	}
	
	public void update(FileEntry fe){
		entityManager.merge(fe);
	}
	
	public FileEntry getFileEntryById(long id){
		return entityManager.find(FileEntry.class, id);
	}
	
	public void delete(long id){
		FileEntry fe = getFileEntryById(id);
		if(fe != null){
			entityManager.remove(fe);
		}
	}
}
