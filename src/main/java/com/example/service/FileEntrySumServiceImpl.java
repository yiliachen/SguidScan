package com.example.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.FileEntrySum;
import com.example.repository.FileEntrySumRepository;

@Service("fileEntrySumService")
public class FileEntrySumServiceImpl implements FileEntrySumService{
	
	@Autowired
	private FileEntrySumRepository fileEntrySumRepository;
	
	@Override
	public Iterable<FileEntrySum> getAllFileEntrySum() {
		return fileEntrySumRepository.findAll();
	}

	@Override
	public FileEntrySum getFileEntrySumById(@PathVariable("id") long id) {
		return fileEntrySumRepository.findById(id);
	}
	
	@Transactional
	public void add(FileEntrySum fe){
		fileEntrySumRepository.save(fe);
	}
	
	public void deleteAll(){
		fileEntrySumRepository.deleteAll();
	}

	@Override
	public Iterable<FileEntrySum> getPathFilteredEntry(String _pPath) {
		return fileEntrySumRepository.findByStartPath(_pPath);
	}
//
//	@Override
//	public Iterable<FileEntrySum> getByDuplicatedValue(String _pFlag) {
//		return fileEntrySumRepository.findBySguidDuplicated(_pFlag);
//	}
//
//	@Override
//	public Iterable<FileEntrySum> getByNullSguid(String _pFlag) {
//		return fileEntrySumRepository.findBySguidIsNull(_pFlag);
//	}
//
//	@Override
//	public Iterable<FileEntrySum> getByNoSguid(String _pFlag) {
//		return fileEntrySumRepository.findByNoSguidTag(_pFlag);
//	}
//
//	@Override
//	public Iterable<FileEntrySum> getByDeIssueSguid(String _pFlag) {
//		// TODO Auto-generated method stub
//		return fileEntrySumRepository.findByDerecSguidDuplicated(_pFlag);
//	}
//
//	@Override
//	public Iterable<FileEntrySum> getByDeSguid(String _pFlag) {
//		return fileEntrySumRepository.findByDerecSguidDuplicated(_pFlag);
//	}

	@Override
	public Iterable<FileEntrySum> getByEqualSpec(final FileEntrySum _pEntrySum) {
		Specification<FileEntrySum> equalspec = Specifications.where(new Specification<FileEntrySum>() {
			@Override
			public Predicate toPredicate(Root<FileEntrySum> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (!StringUtils.isEmpty(_pEntrySum.getSguidDuplicated())) {
					Predicate p1 = cb.equal(root.<String> get("SguidDuplicated"),  StringUtils.trimWhitespace(_pEntrySum.getSguidDuplicated())  );
					predicate.getExpressions().add(p1);
				}
				if (!StringUtils.isEmpty(_pEntrySum.getNoSguidTag())) {
					Predicate p2 = cb.equal(root.<String> get("NoSguidTag"),  StringUtils.trimWhitespace(_pEntrySum.getNoSguidTag())  );
					predicate.getExpressions().add(p2);
				}
				if (!StringUtils.isEmpty(_pEntrySum.getSguidIsNull())) {
					Predicate p3 = cb.equal(root.<String> get("SguidIsNull"),  StringUtils.trimWhitespace(_pEntrySum.getSguidIsNull())  );
					predicate.getExpressions().add(p3);
				}
				if (!StringUtils.isEmpty(_pEntrySum.getDerecSguidDuplicated())) {
					Predicate p4 = cb.equal(root.<String> get("DerecSguidDuplicated"),  StringUtils.trimWhitespace(_pEntrySum.getDerecSguidDuplicated())  );
					predicate.getExpressions().add(p4);
				}
				if (!StringUtils.isEmpty(_pEntrySum.getSguidPopulated())) {
					Predicate p5 = cb.equal(root.<String> get("SguidPopulated"),  StringUtils.trimWhitespace(_pEntrySum.getSguidPopulated())  );
					predicate.getExpressions().add(p5);
				}
				return predicate;
			}});
			return this.fileEntrySumRepository.findAll(equalspec);
	}
}
