package com.example.service;

import org.springframework.stereotype.Service;

import com.example.model.SguidIssueList;
import com.example.model.rowmapper.BranchCounterMapper;
import com.example.model.rowmapper.IssueCountMapper;
import com.example.model.rowmapper.RowDifferMapper;

@Service("sguidIssueListService")
public interface SguidIssueListService {
	public Iterable<SguidIssueList> getAllSguidIssueLists();
	public Iterable<SguidIssueList> getSguidIssueListByFilepath( String __filePath);
	public Iterable<SguidIssueList> getSguidIssueListByBranch( String __branch);
	public Iterable<SguidIssueList> getSguidIssueListByBranchAndFilepath(String __filePath, String __branch, String __issue_type);
	public Iterable<IssueCountMapper> getSguidIssueListFull();
	public Iterable<BranchCounterMapper> getBranchCounterFull();
	public Iterable<RowDifferMapper> getRowDifferMapper();
}