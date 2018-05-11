package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.POCFilePathDAOImpl;
import com.example.model.SguidIssueList;
import com.example.model.rowmapper.BranchCounterMapper;
import com.example.model.rowmapper.IssueCountMapper;
import com.example.model.rowmapper.PocFiles;
import com.example.service.SguidIssueListService;

@RestController
public class SguidIssueListRestController {

	@Autowired
	private SguidIssueListService sguidIssueListService;
	
	@Autowired
	private POCFilePathDAOImpl pocFilePathDAOImpl;
	
	@RequestMapping(value="/sil", method = RequestMethod.GET)
	public Iterable<SguidIssueList> getSguidIssueList(
			@RequestParam("filePath") String filePath,
			@RequestParam("branch") String branch,
			@RequestParam("issue_type") String issue_type){
		return this.sguidIssueListService.getSguidIssueListByBranchAndFilepath(filePath, branch, issue_type);
	}
	
	@ResponseBody
	@RequestMapping(value="/silfull", method=RequestMethod.GET)
	public Iterable<IssueCountMapper> getSguidIssueListFull(){
			return this.sguidIssueListService.getSguidIssueListFull();
	}
	
	@ResponseBody
	@RequestMapping(value="/branchcount", method=RequestMethod.GET)
	public Iterable<BranchCounterMapper> getBranchCount(){
		return this.sguidIssueListService.getBranchCounterFull();
	}
	
	@RequestMapping(path = "/PocMaintain", method = RequestMethod.GET)
	public void ProcessPocMaintain(@RequestParam("mail") String mail ) {
		pocFilePathDAOImpl.CreateReplacePoc(new PocFiles(mail));
	}
	
	@RequestMapping(path = "/DelPoc", method = RequestMethod.GET)
	public void delPoc(@RequestParam("mail") String mail) {
		pocFilePathDAOImpl.deletePoc(new PocFiles(mail));
	}
	
	@ResponseBody
	@RequestMapping(path="/Pocs", method= RequestMethod.GET)
	public Iterable<PocFiles> GetPocs(){
		return pocFilePathDAOImpl.getPocs();
	}
	
	@ResponseBody
	@RequestMapping(path="/RowDiffer", method=RequestMethod.GET)
	public Iterable<RowDifferMapper> GetRowDiffer(){
		return this.sguidIssueListService.getRowDiffer();
	}
}
