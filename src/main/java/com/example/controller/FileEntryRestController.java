package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.FileEntry;
import com.example.service.FileEntryService;

@RestController
public class FileEntryRestController {
	
	@Autowired
	private FileEntryService fileENtryService;
	
//	@RequestMapping(path="/fileentry", method=RequestMethod.GET)
//	public Iterable<FileEntry> getAllFileEntry(@RequestParam("startPath") String startPath){
//		return fileENtryService.getPathFilteredEntry(startPath);
//	}
	
	@RequestMapping(path="/fileentry", method=RequestMethod.GET)
	public Iterable<FileEntry> getAllFileEntry(){
		return fileENtryService.getAllFileEntry();
	}
	
    @RequestMapping(value = "/fileentry/{id}", method = RequestMethod.GET)
	public FileEntry getFileEntryById(@PathVariable("id") long id){
		return fileENtryService.getFileEntryById(id);
	}
    
    @RequestMapping(value = "/fileentry/type/{type}", method = RequestMethod.GET)
	public Iterable<FileEntry> getFileEntryById(@PathVariable("type") String pType){
		return fileENtryService.getFileEntryByType(pType);
	}
}