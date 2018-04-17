package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.FileEntrySum;
import com.example.service.FileEntrySumService;

@RestController
public class FileEntrySumRestController {
	
	@Autowired
	private FileEntrySumService fileENtryService;
	
	@RequestMapping(path="/fileentrysum", method=RequestMethod.GET)
	public Iterable<FileEntrySum> getAllFileEntrySum(@RequestParam("startPath") String startPath){
		return fileENtryService.getPathFilteredEntry(startPath);
	}
	
	@RequestMapping(path="/fileentrysumall", method=RequestMethod.GET)
	public Iterable<FileEntrySum> getAllFileEntrySum(){
		return fileENtryService.getAllFileEntrySum();
	}
	
    @RequestMapping(value = "/fileentrysum/{id}", method = RequestMethod.GET)
	public FileEntrySum getFileEntryById(@PathVariable("id") Integer id){
		return fileENtryService.getFileEntrySumById(id);
	}
    
    @RequestMapping(value = "/fileentrysum/object", method = RequestMethod.GET)
	public Iterable<FileEntrySum> getFileEntryById(
			@RequestParam("sguidPopulated") String sguidPopulated, 
			@RequestParam("startPath") String startPath, 
			@RequestParam("nullSguid") String nullSguid, 
			@RequestParam("dupSguid") String dupSguid, 
			@RequestParam("deSguid") String deSguid, 
			@RequestParam("noSguid") String noSguid
			){
    	FileEntrySum fes = new FileEntrySum();
    	fes.setDerecSguidDuplicated(deSguid);
    	fes.setNoSguidTag(noSguid);
    	fes.setSguidDuplicated(dupSguid);
    	fes.setDerecSguidDuplicated(deSguid);
    	fes.setSguidPopulated(sguidPopulated);
    	fes.setStartPath(startPath);
		return fileENtryService.getByEqualSpec(fes);
	}
}