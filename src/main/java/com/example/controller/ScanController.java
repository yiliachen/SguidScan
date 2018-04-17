package com.example.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.model.BusinessObjects;
import com.example.model.FileEntry;
import com.example.model.FileEntrySum;
import com.example.pojo.FileList;
import com.example.service.BusinessObjectsService;
import com.example.service.FileEntryService;
import com.example.service.FileEntrySumService;
import com.example.xmlpaser.xmlParser;

@Controller
public class ScanController {

	@Autowired
	FileEntryService fileentryservice;
	@Autowired
	FileEntrySumService fileentrysumservice;
	@Autowired
	BusinessObjectsService businessobjectservice;
	

	@RequestMapping(path = "/FileEntrys", method = RequestMethod.POST)
	public String ScanTableSubmit(@ModelAttribute("Scanlist") FileList Scanlist) {
		String[] args = new String[4];
		if (Scanlist.getFilename() != null && Scanlist.getFilename().length() > 0) {
			args[0] = Scanlist.getFilename();
		} else {
			args[0] = "/Users/ming.c.chen/Downloads/filelist.txt";// Default
																	// Path;
		}
		args[1] = "10";// Default Thread count
		args[2] = "attr";
		args[3] = Scanlist.getStartPath();
		// cleancache
		FileEntry _fe = null;
		fileentryservice.deleteAll();

		xmlParser xp = new xmlParser();
		Set<FileEntry> fes = xp.Process(args);
		Iterator<FileEntry> iter = fes.iterator();
		while (iter.hasNext()) {
			fileentryservice.add(iter.next());
		}
		// }
		return "FileEntrys";
	}

	@RequestMapping(path = "/FileEntrys", method = RequestMethod.GET)
	public String GetSingleFileDetail(@RequestParam("filename") String filename) {

		fileentryservice.deleteAll();

		xmlParser xp = new xmlParser();
		Set<FileEntry> fes = xp.Process(filename);
		Iterator<FileEntry> iter = fes.iterator();
		while (iter.hasNext()) {
			fileentryservice.add(iter.next());
		}
		return "FileEntrys";
	}

	public Boolean onSameDay(Date day1, Date day2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(day1);
		cal2.setTime(day2);
		Boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
		return sameDay;
	}

	@RequestMapping(path = "/ProcessResults", method = RequestMethod.POST)
	public String ScanProcessSubmit(@ModelAttribute("Scanlist") FileList Scanlist, Model model) {
		model.addAttribute("startPath", Scanlist.getStartPath());
		String[] args = new String[4];
		if (Scanlist.getFilename() != null && Scanlist.getFilename().length() > 0) {
			args[0] = Scanlist.getFilename();
		} else {
			args[0] = "/net/slc09pot.us.oracle.com/scratch/SeedProcess/fulllist_sorted";// Default
			// Path;
//			 args[0] = "/Users/ming.c.chen/Downloads/filelist.txt";
		}
		args[1] = "10";// Default Thread count
		args[2] = "file";
		if(Scanlist.getStartPath() != null && Scanlist.getStartPath().length() > 0){
		args[3] = Scanlist.getStartPath();
		}else{
			args[3] = "";
		}
		// cleancache

		FileEntrySum _fes = null;
		if (fileentrysumservice.getPathFilteredEntry(Scanlist.getStartPath()).iterator().hasNext())
			_fes = fileentrysumservice.getPathFilteredEntry(Scanlist.getStartPath()).iterator().next();
		if (_fes == null || !onSameDay(_fes.getTimestamp(), new Date())) {
			fileentrysumservice.deleteAll();

			xmlParser xp = new xmlParser();
			Set<FileEntrySum> fes = xp.Process(args);
			Iterator<FileEntrySum> iter = fes.iterator();
			while (iter.hasNext()) {
				fileentrysumservice.add(iter.next());
			}
		}
		return "ProcessResults";
	}
	
	@RequestMapping(path = "/IssueList", method = RequestMethod.POST)
	public String CheckIssue(@ModelAttribute("Issuelist") FileList Issuelist, Model model) {
		model.addAttribute("startPath", Issuelist.getStartPath());
		return "NullDup";
	}

	@ResponseBody
	@RequestMapping(path = "/branchesList", method = RequestMethod.GET)
	public Object getBranchList(@RequestParam("path") String pPath) {
		File _ldir = new File(pPath);
		ArrayList<File> lfileList = null;
		try {
			File[] files;
			if (_ldir.exists() && _ldir.isDirectory()) {
				files = _ldir.listFiles();
				lfileList = new ArrayList<File>(Arrays.asList(files));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfileList;
	}

	@RequestMapping(path = "/meta/reload", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void ScanMeaData(@RequestParam("filename") String __filename, @RequestParam("type") String __type) {
		String[] _args = new String[2];
		_args[0] = __filename;
		_args[1] = __type;
		xmlParser xp = new xmlParser();
		String _typeClass = null;
		switch (__type) {
		case "AM":
			_typeClass = "amProcessor";
			break;
		case "EO":
			_typeClass = "eoProcessor";
			break;
		case "VO":
			_typeClass = "voProcessor";
			break;
		case "SDF":
			_typeClass = "sdfProcessor";
			break;
		case "DBO":
			_typeClass = "dbobjectProcessor";
			break;
		}
		xp.Process(_args, _typeClass);
	}
	
	@RequestMapping(path="/BusinessObjects", method = RequestMethod.GET)
	public String getBusinessObjectPage(Model __model){
		__model.addAttribute("BusinessObjectsBinding", new BusinessObjects());
		return "BusinessObjects";
	}
	@ResponseBody
	@RequestMapping(path="/boData", method = RequestMethod.POST)
	public Iterable<BusinessObjects> getBOData(@ModelAttribute("BusinessObjectsBinding") BusinessObjects __bo, @RequestParam("pageindex") Integer pageIndex){
		 Page<BusinessObjects> page = this.businessobjectservice.getPagerListByCondition(pageIndex, 10000, __bo);
		 return page;
	}
}