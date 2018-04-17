package com.example.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.model.SguidIssueList;
import com.example.model.rowmapper.BranchCounterMapper;
import com.example.model.rowmapper.IssueCountMapper;
import com.example.repository.SguidIssueListRepository;

@Service("sguidIssueList")
public class SguidIssueListServiceImpl implements SguidIssueListService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	SguidIssueListRepository sguidissuelistrepository;
	
	@Override
	public Iterable<SguidIssueList> getAllSguidIssueLists() {
		return sguidissuelistrepository.findAll();
	}

	@Override
	public Iterable<SguidIssueList> getSguidIssueListByFilepath(String __filePath) {
		return sguidissuelistrepository.findByFilepath(__filePath);
	}

	@Override
	public Iterable<SguidIssueList> getSguidIssueListByBranch(String __branch) {
		return sguidissuelistrepository.findByBranch(__branch);
	}

	@Override
	public Iterable<SguidIssueList> getSguidIssueListByBranchAndFilepath(final String __filePath, final String __branch, final String __issue_type) {
		// TODO Auto-generated method stub
		Specification<SguidIssueList> equalspec = Specifications.where(new Specification<SguidIssueList>() {
			@Override
			public Predicate toPredicate(Root<SguidIssueList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				
				if (!StringUtils.isEmpty(__filePath)) {
					Predicate p4 = cb.equal(root.<String> get("filepath"),  StringUtils.trimWhitespace(__filePath)  );
					predicate.getExpressions().add(p4);
				}
				if (!StringUtils.isEmpty(__branch)) {
					Predicate p5 = cb.equal(root.<String> get("branch"),  StringUtils.trimWhitespace(__branch)  );
					predicate.getExpressions().add(p5);
				}
				if(!StringUtils.isEmpty(__issue_type)){
					Predicate p6 = cb.equal(root.<String> get("issue_type"),  StringUtils.trimWhitespace(__issue_type)  );
					predicate.getExpressions().add(p6);
				}
				return predicate;
			}});
			return this.sguidissuelistrepository.findAll(equalspec);
	}

	@Override
	public Iterable<IssueCountMapper> getSguidIssueListFull() {
		String sql = "select branch, filepath, poc.mail, product, NullSGUID, Duplicated, BULKSEEDMissing, ERROR_DUPSGUID, ERROR_SGUIDDIFF from "+
				"(select branch, "+
						"filepath, "+
						"case when product = 'hrx' then substring(substring_index(substring_index(filepath,'/',6),'/',-1) ,4,5) else product end as product, "+ 
						"NullSGUID, "+
						"Duplicated, "+
						"BULKSEEDMissing,"+ 
						"ERROR_DUPSGUID, "+
						"ERROR_SGUIDDIFF "+
						 "from (select branch, filepath, substring_index(substring_index(filepath,'/',3),'/',-1) as product,"+
						"sum(case when issue_type = 'NullSGUID' then 1 else 0 end) as 'NullSGUID', "+
						"sum(case when issue_type = 'Duplicated' then 1 else 0 end ) as 'Duplicated', "+
						"sum(case when issue_type = 'BULKSEEDMissing' then 1 else 0 end) as 'BULKSEEDMissing', "+
						"sum(case when issue_type = 'ERROR_DUPSGUID' then 1 else 0 end ) as 'ERROR_DUPSGUID', "+
						"sum(case when issue_type = 'ERROR_SGUIDDIFF' then 1 else 0 end) as 'ERROR_SGUIDDIFF' "+ 
						"from sguid_issue_list issuelist group by branch, filepath, substring_index(substring_index(filepath,'/',3),'/',-1)) tmp) tmp2 "+
						"left join pocfiles poc on tmp2.product = poc.path";
						return (List<IssueCountMapper>) this.jdbcTemplate.query(sql, new IssueCountMapper());
	}

	@Override
	public Iterable<BranchCounterMapper> getBranchCounterFull() {
		String sql = "select branch, " + 
				"case when product = 'hrx' then substring(substring_index(substring_index(filepath,'/',6),'/',-1) ,4,5) else product end as product, " + 
				"NullSGUID, " + 
				"Duplicated, " + 
				"BULKSEEDMissing, " + 
				"ERROR_DUPSGUID, " + 
				"ERROR_SGUIDDIFF" + 
				" from (select branch, filepath, substring_index(substring_index(filepath,'/',3),'/',-1) as product," + 
				"sum(case when issue_type = 'NullSGUID' then 1 else 0 end) as 'NullSGUID', " + 
				"sum(case when issue_type = 'Duplicated' then 1 else 0 end ) as 'Duplicated'," + 
				"sum(case when issue_type = 'BULKSEEDMissing' then 1 else 0 end) as 'BULKSEEDMissing'," + 
				"sum(case when issue_type = 'ERROR_DUPSGUID' then 1 else 0 end ) as 'ERROR_DUPSGUID'," + 
				"sum(case when issue_type = 'ERROR_SGUIDDIFF' then 1 else 0 end) as 'ERROR_SGUIDDIFF'" + 
				"from sguid_issue_list issuelist group by branch, substring_index(substring_index(filepath,'/',3),'/',-1)) tmp";
		return (List<BranchCounterMapper>)this.jdbcTemplate.query(sql, new BranchCounterMapper());
	}
	
}