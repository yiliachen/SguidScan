package com.example.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.FileEntry;
import com.example.repository.FileEntryRepository;

@Service("fileEntryService")
public class FileEntryServiceImpl implements FileEntryService{
	
	@Autowired
	private FileEntryRepository fileEntryRepository;
	
	@Override
	public Iterable<FileEntry> getAllFileEntry() {
		// TODO Auto-generated method stub
		return fileEntryRepository.findAll();
	}

	@Override
	public FileEntry getFileEntryById(@PathVariable("id") long id) {
		return fileEntryRepository.findOne(id);
	}
	
	@Transactional
	public void add(FileEntry fe){
		fileEntryRepository.save(fe);
	}
	
	public void deleteAll(){
		fileEntryRepository.deleteAll();
	}

	@Override
	public Iterable<FileEntry> getPathFilteredEntry(String _pPath) {
		Iterator iter = fileEntryRepository.findAll().iterator();
		Set<Long> ids = new HashSet<Long>();
		while(iter.hasNext()){
			FileEntry _lfe = (FileEntry) iter.next();
			if(_lfe.getStartPath().equals(_pPath)){
				ids.add(new Long(_lfe.getId()));
			}
		}
		return fileEntryRepository.findAll(ids);
	}

	@Override
	public Iterable<FileEntry> getFileEntryByType(String type) {
		// TODO Auto-generated method stub
		return this.fileEntryRepository.findByType(type);
	}
	
}
