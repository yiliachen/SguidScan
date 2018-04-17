package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.EntityObject;
import com.example.model.FileEntry;

@Repository("fileEntryRepository")
public interface FileEntryRepository extends CrudRepository<FileEntry, Long> {
	Iterable<FileEntry> findByType(String Type);
}
