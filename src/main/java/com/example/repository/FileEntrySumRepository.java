package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.model.FileEntrySum;

@Repository("fileEntrySumRepository")
public interface FileEntrySumRepository extends JpaRepository<FileEntrySum,Integer>, JpaSpecificationExecutor<FileEntrySum> {
	Iterable<FileEntrySum> findByStartPath(String startPath);
//	Iterable<FileEntrySum> findBySguidPopulated(String _pFlag);
//	Iterable<FileEntrySum> findBySguidDuplicated(String _pFlag);
//	Iterable<FileEntrySum> findByDerecSguidDuplicated(String _pFlag);
//	Iterable<FileEntrySum> findBySguidIsNull(String _pFlag);
//	Iterable<FileEntrySum> findByNoSguidTag(String _pFlag);
	FileEntrySum findById(long id);
}
