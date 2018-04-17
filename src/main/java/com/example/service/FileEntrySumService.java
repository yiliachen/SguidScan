package com.example.service;


import com.example.model.FileEntrySum;

public interface FileEntrySumService {
	public Iterable<FileEntrySum> getAllFileEntrySum();
	public void add(FileEntrySum fe);
	public void deleteAll();
	public Iterable<FileEntrySum> getPathFilteredEntry( String _pPath);
//	public Iterable<FileEntrySum> getByDuplicatedValue( String _pFlag);
//	public Iterable<FileEntrySum> getByNullSguid( String _pFlag);
//	public Iterable<FileEntrySum> getByNoSguid( String _pFlag);
//	public Iterable<FileEntrySum> getByDeIssueSguid( String _pFlag);
//	public Iterable<FileEntrySum> getByDeSguid( String _pFlag);
	public Iterable<FileEntrySum> getByEqualSpec(final FileEntrySum _pEntrySum);
	FileEntrySum getFileEntrySumById(long id);
}
