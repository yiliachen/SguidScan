package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.model.SguidIssueList;

public interface SguidIssueListRepository extends JpaRepository<SguidIssueList, Long>,JpaSpecificationExecutor<SguidIssueList> {
	Iterable<SguidIssueList> findByFilepath(String __filepath);
	Iterable<SguidIssueList> findByBranch(String __branch);
}
