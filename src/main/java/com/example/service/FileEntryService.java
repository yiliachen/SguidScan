package com.example.service;

import com.example.model.FileEntry;

public interface FileEntryService {
	public Iterable<FileEntry> getAllFileEntry();
	public FileEntry getFileEntryById(long id);
	public Iterable<FileEntry> getFileEntryByType(String type);
	public void add(FileEntry fe);
	public void deleteAll();
	public Iterable<FileEntry> getPathFilteredEntry(String _pPath );
}
