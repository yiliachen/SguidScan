package com.example.service;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.model.BusinessObjects;
import com.example.model.rowmapper.IssueCountMapper;
import com.example.repository.BusinessObjectsRepository;

@Service
public class BusinessObjectsServiceImpl implements BusinessObjectsService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BusinessObjectsRepository businessObjectsRepository;

	public void create(BusinessObjects entity) {
		businessObjectsRepository.saveAndFlush(entity);
	}

	public void update(BusinessObjects entity) {
		businessObjectsRepository.saveAndFlush(entity);
	}

	public BusinessObjects getByPrimaryKey(Integer key) {
		return businessObjectsRepository.findOne(key);
	}

	public void deleteByPrimaryKey(Integer key) {
		businessObjectsRepository.delete(key);
	}

	public Page<BusinessObjects> getPagerList(Integer pageIndex, Integer pageSize) {
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		return businessObjectsRepository.findAll(pageable);
	}

	public Page<BusinessObjects> getPagerList(Integer pageIndex, Integer pageSize, String sortColumn, String sortType) {
		Sort sort = null;
		if ("ASC".equalsIgnoreCase(sortType)) {
			sort = new Sort(Direction.ASC, sortColumn);
		}
		if ("DESC".equalsIgnoreCase(sortType)) {
			sort = new Sort(Direction.DESC, sortColumn);
		}
		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		return businessObjectsRepository.findAll(pageable);
	}

	public Page<BusinessObjects> getPagerListByCondition(Integer pageIndex, Integer pageSize, final BusinessObjects entity) {
		if (entity == null) {
			return getPagerList(pageIndex, pageSize);
		}
		Specification<BusinessObjects> spec = Specifications.where(new Specification<BusinessObjects>() {
			@Override
			public Predicate toPredicate(Root<BusinessObjects> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (!StringUtils.isEmpty(entity.getVo_def())) {
					Predicate p1 = cb.like(root.<String> get("vo_def"),"%" + StringUtils.trimWhitespace(entity.getVo_def()) + "%");
					predicate.getExpressions().add(p1);
				}
				if (!StringUtils.isEmpty(entity.getVo_name())) {
					Predicate p2 = cb.like(root.<String> get("vo_name"),"%" + StringUtils.trimWhitespace(entity.getVo_name()) + "%");
					predicate.getExpressions().add(p2);
				}
				if (!StringUtils.isEmpty(entity.getVo_extends())) {
					Predicate p3 = cb.like(root.<String> get("vo_extends"),"%" + StringUtils.trimWhitespace(entity.getVo_extends()) + "%");
					predicate.getExpressions().add(p3);
				}
				if (!StringUtils.isEmpty(entity.getVo_path())) {
					Predicate p4 = cb.like(root.<String> get("vo_path"),"%" + StringUtils.trimWhitespace(entity.getVo_path()) + "%");
					predicate.getExpressions().add(p4);
				}
				if (!StringUtils.isEmpty(entity.getExtra_name())) {
					Predicate p5 = cb.like(root.<String> get("extra_name"),"%" + StringUtils.trimWhitespace(entity.getExtra_name()) + "%");
					predicate.getExpressions().add(p5);
				}
				if (!StringUtils.isEmpty(entity.getExtra_def())) {
					Predicate p6 = cb.like(root.<String> get("extra_def"),"%" + StringUtils.trimWhitespace(entity.getExtra_def()) + "%");
					predicate.getExpressions().add(p6);
				}
				if (!StringUtils.isEmpty(entity.getEo_name())) {
					Predicate p7 = cb.like(root.<String> get("eo_name"),"%" + StringUtils.trimWhitespace(entity.getEo_name()) + "%");
					predicate.getExpressions().add(p7);
				}
				if (!StringUtils.isEmpty(entity.getEo_path())) {
					Predicate p8 = cb.like(root.<String> get("eo_path"),"%" + StringUtils.trimWhitespace(entity.getEo_path()) + "%");
					predicate.getExpressions().add(p8);
				}
				if (!StringUtils.isEmpty(entity.getVo_entity_usage_obj_def())) {
					Predicate p9 = cb.like(root.<String> get("vo_entity_usage_obj_def"),"%" + StringUtils.trimWhitespace(entity.getVo_entity_usage_obj_def()) + "%");
					predicate.getExpressions().add(p9);
				}
				if (!StringUtils.isEmpty(entity.getDbo_type())) {
					Predicate p10 = cb.like(root.<String> get("dbo_type"),"%" + StringUtils.trimWhitespace(entity.getDbo_type()) + "%");
					predicate.getExpressions().add(p10);
				}
				if (!StringUtils.isEmpty(entity.getDbo_name())) {
					Predicate p11 = cb.like(root.<String> get("dbo_name"),"%" + StringUtils.trimWhitespace(entity.getDbo_name()) + "%");
					predicate.getExpressions().add(p11);
				}
				if (!StringUtils.isEmpty(entity.getDbo_col_name())) {
					Predicate p12 = cb.like(root.<String> get("dbo_col_name"),"%" + StringUtils.trimWhitespace(entity.getDbo_col_name()) + "%");
					predicate.getExpressions().add(p12);
				}
				if (!StringUtils.isEmpty(entity.getDbo_path())) {
					Predicate p13 = cb.like(root.<String> get("dbo_path"),"%" + StringUtils.trimWhitespace(entity.getDbo_path()) + "%");
					predicate.getExpressions().add(p13);
				}
				if (!StringUtils.isEmpty(entity.getSdf_path())) {
					Predicate p14 = cb.like(root.<String> get("sdf_path"),"%" + StringUtils.trimWhitespace(entity.getSdf_path()) + "%");
					predicate.getExpressions().add(p14);
				}
				if (!StringUtils.isEmpty(entity.getSdf_vo())) {
					Predicate p15 = cb.like(root.<String> get("sdf_vo"),"%" + StringUtils.trimWhitespace(entity.getSdf_vo()) + "%");
					predicate.getExpressions().add(p15);
				}
				if (!StringUtils.isEmpty(entity.getSdf_am())) {
					Predicate p16 = cb.like(root.<String> get("sdf_am"),"%" + StringUtils.trimWhitespace(entity.getSdf_am()) + "%");
					predicate.getExpressions().add(p16);
				}
				if (!StringUtils.isEmpty(entity.getAm_name())) {
					Predicate p17 = cb.like(root.<String> get("am_name"),"%" + StringUtils.trimWhitespace(entity.getAm_name()) + "%");
					predicate.getExpressions().add(p17);
				}
				if (!StringUtils.isEmpty(entity.getAm_vo_usage_name())) {
					Predicate p18 = cb.like(root.<String> get("am_vo_usage_name"),"%" + StringUtils.trimWhitespace(entity.getAm_vo_usage_name()) + "%");
					predicate.getExpressions().add(p18);
				}
				if (!StringUtils.isEmpty(entity.getAm_vo_usage_object_def())) {
					Predicate p19 = cb.like(root.<String> get("am_vo_usage_object_def"),"%" + StringUtils.trimWhitespace(entity.getAm_vo_usage_object_def()) + "%");
					predicate.getExpressions().add(p19);
				}
				return predicate;
			}
		});
		Specification<BusinessObjects> equalspec = Specifications.where(new Specification<BusinessObjects>() {
			@Override
			public Predicate toPredicate(Root<BusinessObjects> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (!StringUtils.isEmpty(entity.getVo_def())) {
					Predicate p1 = cb.equal(root.<String> get("vo_def"),  StringUtils.trimWhitespace(entity.getVo_def())  );
					predicate.getExpressions().add(p1);
				}
				if (!StringUtils.isEmpty(entity.getVo_name())) {
					Predicate p2 = cb.equal(root.<String> get("vo_name"),  StringUtils.trimWhitespace(entity.getVo_name())  );
					predicate.getExpressions().add(p2);
				}
				if (!StringUtils.isEmpty(entity.getVo_extends())) {
					Predicate p3 = cb.equal(root.<String> get("vo_extends"),  StringUtils.trimWhitespace(entity.getVo_extends())  );
					predicate.getExpressions().add(p3);
				}
				if (!StringUtils.isEmpty(entity.getVo_path())) {
					Predicate p4 = cb.like(root.<String> get("vo_path"), "%"+ StringUtils.trimWhitespace(entity.getVo_path()) + "%" );
					predicate.getExpressions().add(p4);
				}
				if (!StringUtils.isEmpty(entity.getExtra_name())) {
					Predicate p5 = cb.equal(root.<String> get("extra_name"),  StringUtils.trimWhitespace(entity.getExtra_name())  );
					predicate.getExpressions().add(p5);
				}
				if (!StringUtils.isEmpty(entity.getExtra_def())) {
					Predicate p6 = cb.equal(root.<String> get("extra_def"),  StringUtils.trimWhitespace(entity.getExtra_def())  );
					predicate.getExpressions().add(p6);
				}
				if (!StringUtils.isEmpty(entity.getEo_name())) {
					Predicate p7 = cb.equal(root.<String> get("eo_name"),  StringUtils.trimWhitespace(entity.getEo_name())  );
					predicate.getExpressions().add(p7);
				}
				if (!StringUtils.isEmpty(entity.getEo_path())) {
					Predicate p8 = cb.like(root.<String> get("eo_path"),  "%" +StringUtils.trimWhitespace(entity.getEo_path()) +"%" );
					predicate.getExpressions().add(p8);
				}
				if (!StringUtils.isEmpty(entity.getVo_entity_usage_obj_def())) {
					Predicate p9 = cb.equal(root.<String> get("vo_entity_usage_obj_def"),  StringUtils.trimWhitespace(entity.getVo_entity_usage_obj_def())  );
					predicate.getExpressions().add(p9);
				}
				if (!StringUtils.isEmpty(entity.getDbo_type())) {
					Predicate p10 = cb.equal(root.<String> get("dbo_type"),  StringUtils.trimWhitespace(entity.getDbo_type())  );
					predicate.getExpressions().add(p10);
				}
				if (!StringUtils.isEmpty(entity.getDbo_name())) {
					Predicate p11 = cb.equal(root.<String> get("dbo_name"),  StringUtils.trimWhitespace(entity.getDbo_name())  );
					predicate.getExpressions().add(p11);
				}
				if (!StringUtils.isEmpty(entity.getDbo_col_name())) {
					Predicate p12 = cb.equal(root.<String> get("dbo_col_name"),  StringUtils.trimWhitespace(entity.getDbo_col_name())  );
					predicate.getExpressions().add(p12);
				}
				if (!StringUtils.isEmpty(entity.getDbo_path())) {
					Predicate p13 = cb.like(root.<String> get("dbo_path"),  "%"+StringUtils.trimWhitespace(entity.getDbo_path()) +"%" );
					predicate.getExpressions().add(p13);
				}
				if (!StringUtils.isEmpty(entity.getSdf_path())) {
					Predicate p14 = cb.like(root.<String> get("sdf_path"),  "%"+StringUtils.trimWhitespace(entity.getSdf_path()) +"%" );
					predicate.getExpressions().add(p14);
				}
				if (!StringUtils.isEmpty(entity.getSdf_vo())) {
					Predicate p15 = cb.equal(root.<String> get("sdf_vo"),  StringUtils.trimWhitespace(entity.getSdf_vo())  );
					predicate.getExpressions().add(p15);
				}
				if (!StringUtils.isEmpty(entity.getSdf_am())) {
					Predicate p16 = cb.equal(root.<String> get("sdf_am"),  StringUtils.trimWhitespace(entity.getSdf_am())  );
					predicate.getExpressions().add(p16);
				}
				if (!StringUtils.isEmpty(entity.getAm_name())) {
					Predicate p17 = cb.equal(root.<String> get("am_name"),  StringUtils.trimWhitespace(entity.getAm_name())  );
					predicate.getExpressions().add(p17);
				}
				if (!StringUtils.isEmpty(entity.getAm_vo_usage_name())) {
					Predicate p18 = cb.equal(root.<String> get("am_vo_usage_name"),  StringUtils.trimWhitespace(entity.getAm_vo_usage_name())  );
					predicate.getExpressions().add(p18);
				}
				if (!StringUtils.isEmpty(entity.getAm_vo_usage_object_def())) {
					Predicate p19 = cb.equal(root.<String> get("am_vo_usage_object_def"),  StringUtils.trimWhitespace(entity.getAm_vo_usage_object_def())  );
					predicate.getExpressions().add(p19);
				}
				return predicate;
			}
		});
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		return businessObjectsRepository.findAll(equalspec, pageable);
	}

	public Page<BusinessObjects> getPagerListByCondition(Integer pageIndex, Integer pageSize, String sortColumn, String sortType, final BusinessObjects entity) {
		if (entity == null) {
			return getPagerList(pageIndex, pageSize, sortColumn, sortType);
		}
		Specification<BusinessObjects> spec = Specifications.where(new Specification<BusinessObjects>() {
			@Override
			public Predicate toPredicate(Root<BusinessObjects> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (!StringUtils.isEmpty(entity.getVo_def())) {
					Predicate p1 = cb.like(root.<String> get("vo_def"),"%" + StringUtils.trimWhitespace(entity.getVo_def()) + "%");
					predicate.getExpressions().add(p1);
				}
				if (!StringUtils.isEmpty(entity.getVo_name())) {
					Predicate p2 = cb.like(root.<String> get("vo_name"),"%" + StringUtils.trimWhitespace(entity.getVo_name()) + "%");
					predicate.getExpressions().add(p2);
				}
				if (!StringUtils.isEmpty(entity.getVo_extends())) {
					Predicate p3 = cb.like(root.<String> get("vo_extends"),"%" + StringUtils.trimWhitespace(entity.getVo_extends()) + "%");
					predicate.getExpressions().add(p3);
				}
				if (!StringUtils.isEmpty(entity.getVo_path())) {
					Predicate p4 = cb.like(root.<String> get("vo_path"),"%" + StringUtils.trimWhitespace(entity.getVo_path()) + "%");
					predicate.getExpressions().add(p4);
				}
				if (!StringUtils.isEmpty(entity.getExtra_name())) {
					Predicate p5 = cb.like(root.<String> get("extra_name"),"%" + StringUtils.trimWhitespace(entity.getExtra_name()) + "%");
					predicate.getExpressions().add(p5);
				}
				if (!StringUtils.isEmpty(entity.getExtra_def())) {
					Predicate p6 = cb.like(root.<String> get("extra_def"),"%" + StringUtils.trimWhitespace(entity.getExtra_def()) + "%");
					predicate.getExpressions().add(p6);
				}
				if (!StringUtils.isEmpty(entity.getEo_name())) {
					Predicate p7 = cb.like(root.<String> get("eo_name"),"%" + StringUtils.trimWhitespace(entity.getEo_name()) + "%");
					predicate.getExpressions().add(p7);
				}
				if (!StringUtils.isEmpty(entity.getEo_path())) {
					Predicate p8 = cb.like(root.<String> get("eo_path"),"%" + StringUtils.trimWhitespace(entity.getEo_path()) + "%");
					predicate.getExpressions().add(p8);
				}
				if (!StringUtils.isEmpty(entity.getVo_entity_usage_obj_def())) {
					Predicate p9 = cb.like(root.<String> get("vo_entity_usage_obj_def"),"%" + StringUtils.trimWhitespace(entity.getVo_entity_usage_obj_def()) + "%");
					predicate.getExpressions().add(p9);
				}
				if (!StringUtils.isEmpty(entity.getDbo_type())) {
					Predicate p10 = cb.like(root.<String> get("dbo_type"),"%" + StringUtils.trimWhitespace(entity.getDbo_type()) + "%");
					predicate.getExpressions().add(p10);
				}
				if (!StringUtils.isEmpty(entity.getDbo_name())) {
					Predicate p11 = cb.like(root.<String> get("dbo_name"),"%" + StringUtils.trimWhitespace(entity.getDbo_name()) + "%");
					predicate.getExpressions().add(p11);
				}
				if (!StringUtils.isEmpty(entity.getDbo_col_name())) {
					Predicate p12 = cb.like(root.<String> get("dbo_col_name"),"%" + StringUtils.trimWhitespace(entity.getDbo_col_name()) + "%");
					predicate.getExpressions().add(p12);
				}
				if (!StringUtils.isEmpty(entity.getDbo_path())) {
					Predicate p13 = cb.like(root.<String> get("dbo_path"),"%" + StringUtils.trimWhitespace(entity.getDbo_path()) + "%");
					predicate.getExpressions().add(p13);
				}
				if (!StringUtils.isEmpty(entity.getSdf_path())) {
					Predicate p14 = cb.like(root.<String> get("sdf_path"),"%" + StringUtils.trimWhitespace(entity.getSdf_path()) + "%");
					predicate.getExpressions().add(p14);
				}
				if (!StringUtils.isEmpty(entity.getSdf_vo())) {
					Predicate p15 = cb.like(root.<String> get("sdf_vo"),"%" + StringUtils.trimWhitespace(entity.getSdf_vo()) + "%");
					predicate.getExpressions().add(p15);
				}
				if (!StringUtils.isEmpty(entity.getSdf_am())) {
					Predicate p16 = cb.like(root.<String> get("sdf_am"),"%" + StringUtils.trimWhitespace(entity.getSdf_am()) + "%");
					predicate.getExpressions().add(p16);
				}
				if (!StringUtils.isEmpty(entity.getAm_name())) {
					Predicate p17 = cb.like(root.<String> get("am_name"),"%" + StringUtils.trimWhitespace(entity.getAm_name()) + "%");
					predicate.getExpressions().add(p17);
				}
				if (!StringUtils.isEmpty(entity.getAm_vo_usage_name())) {
					Predicate p18 = cb.like(root.<String> get("am_vo_usage_name"),"%" + StringUtils.trimWhitespace(entity.getAm_vo_usage_name()) + "%");
					predicate.getExpressions().add(p18);
				}
				if (!StringUtils.isEmpty(entity.getAm_vo_usage_object_def())) {
					Predicate p19 = cb.like(root.<String> get("am_vo_usage_object_def"),"%" + StringUtils.trimWhitespace(entity.getAm_vo_usage_object_def()) + "%");
					predicate.getExpressions().add(p19);
				}
				return predicate;
			}
		});
		
		
		
		Specification<BusinessObjects> equalspec = Specifications.where(new Specification<BusinessObjects>() {
			@Override
			public Predicate toPredicate(Root<BusinessObjects> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (!StringUtils.isEmpty(entity.getVo_def())) {
					Predicate p1 = cb.equal(root.<String> get("vo_def"),  StringUtils.trimWhitespace(entity.getVo_def())  );
					predicate.getExpressions().add(p1);
				}
				if (!StringUtils.isEmpty(entity.getVo_name())) {
					Predicate p2 = cb.equal(root.<String> get("vo_name"),  StringUtils.trimWhitespace(entity.getVo_name())  );
					predicate.getExpressions().add(p2);
				}
				if (!StringUtils.isEmpty(entity.getVo_extends())) {
					Predicate p3 = cb.equal(root.<String> get("vo_extends"),  StringUtils.trimWhitespace(entity.getVo_extends())  );
					predicate.getExpressions().add(p3);
				}
				if (!StringUtils.isEmpty(entity.getVo_path())) {
					Predicate p4 = cb.equal(root.<String> get("vo_path"),  StringUtils.trimWhitespace(entity.getVo_path())  );
					predicate.getExpressions().add(p4);
				}
				if (!StringUtils.isEmpty(entity.getExtra_name())) {
					Predicate p5 = cb.equal(root.<String> get("extra_name"),  StringUtils.trimWhitespace(entity.getExtra_name())  );
					predicate.getExpressions().add(p5);
				}
				if (!StringUtils.isEmpty(entity.getExtra_def())) {
					Predicate p6 = cb.equal(root.<String> get("extra_def"),  StringUtils.trimWhitespace(entity.getExtra_def())  );
					predicate.getExpressions().add(p6);
				}
				if (!StringUtils.isEmpty(entity.getEo_name())) {
					Predicate p7 = cb.equal(root.<String> get("eo_name"),  StringUtils.trimWhitespace(entity.getEo_name())  );
					predicate.getExpressions().add(p7);
				}
				if (!StringUtils.isEmpty(entity.getEo_path())) {
					Predicate p8 = cb.equal(root.<String> get("eo_path"),  StringUtils.trimWhitespace(entity.getEo_path())  );
					predicate.getExpressions().add(p8);
				}
				if (!StringUtils.isEmpty(entity.getVo_entity_usage_obj_def())) {
					Predicate p9 = cb.equal(root.<String> get("vo_entity_usage_obj_def"),  StringUtils.trimWhitespace(entity.getVo_entity_usage_obj_def())  );
					predicate.getExpressions().add(p9);
				}
				if (!StringUtils.isEmpty(entity.getDbo_type())) {
					Predicate p10 = cb.equal(root.<String> get("dbo_type"),  StringUtils.trimWhitespace(entity.getDbo_type())  );
					predicate.getExpressions().add(p10);
				}
				if (!StringUtils.isEmpty(entity.getDbo_name())) {
					Predicate p11 = cb.equal(root.<String> get("dbo_name"),  StringUtils.trimWhitespace(entity.getDbo_name())  );
					predicate.getExpressions().add(p11);
				}
				if (!StringUtils.isEmpty(entity.getDbo_col_name())) {
					Predicate p12 = cb.equal(root.<String> get("dbo_col_name"),  StringUtils.trimWhitespace(entity.getDbo_col_name())  );
					predicate.getExpressions().add(p12);
				}
				if (!StringUtils.isEmpty(entity.getDbo_path())) {
					Predicate p13 = cb.equal(root.<String> get("dbo_path"),  StringUtils.trimWhitespace(entity.getDbo_path())  );
					predicate.getExpressions().add(p13);
				}
				if (!StringUtils.isEmpty(entity.getSdf_path())) {
					Predicate p14 = cb.equal(root.<String> get("sdf_path"),  StringUtils.trimWhitespace(entity.getSdf_path())  );
					predicate.getExpressions().add(p14);
				}
				if (!StringUtils.isEmpty(entity.getSdf_vo())) {
					Predicate p15 = cb.equal(root.<String> get("sdf_vo"),  StringUtils.trimWhitespace(entity.getSdf_vo())  );
					predicate.getExpressions().add(p15);
				}
				if (!StringUtils.isEmpty(entity.getSdf_am())) {
					Predicate p16 = cb.equal(root.<String> get("sdf_am"),  StringUtils.trimWhitespace(entity.getSdf_am())  );
					predicate.getExpressions().add(p16);
				}
				if (!StringUtils.isEmpty(entity.getAm_name())) {
					Predicate p17 = cb.equal(root.<String> get("am_name"),  StringUtils.trimWhitespace(entity.getAm_name())  );
					predicate.getExpressions().add(p17);
				}
				if (!StringUtils.isEmpty(entity.getAm_vo_usage_name())) {
					Predicate p18 = cb.equal(root.<String> get("am_vo_usage_name"),  StringUtils.trimWhitespace(entity.getAm_vo_usage_name())  );
					predicate.getExpressions().add(p18);
				}
				if (!StringUtils.isEmpty(entity.getAm_vo_usage_object_def())) {
					Predicate p19 = cb.equal(root.<String> get("am_vo_usage_object_def"),  StringUtils.trimWhitespace(entity.getAm_vo_usage_object_def())  );
					predicate.getExpressions().add(p19);
				}
				return predicate;
			}
		});
		Sort sort = null;
		if ("ASC".equalsIgnoreCase(sortType)) {
			sort = new Sort(Direction.ASC, sortColumn);
		}
		if ("DESC".equalsIgnoreCase(sortType)) {
			sort = new Sort(Direction.DESC, sortColumn);
		}
		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		return businessObjectsRepository.findAll(spec, pageable);
	}

	public long getTotalRecordCount() {
		return businessObjectsRepository.count();
	}
	
	

	@Override
	public Iterable<BusinessObjects> getByEqualSpec(final BusinessObjects entity) {
		Specification<BusinessObjects> equalspec = Specifications.where(new Specification<BusinessObjects>() {
			@Override
			public Predicate toPredicate(Root<BusinessObjects> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (!StringUtils.isEmpty(entity.getVo_def())) {
					Predicate p1 = cb.equal(root.<String> get("vo_def"),  StringUtils.trimWhitespace(entity.getVo_def())  );
					predicate.getExpressions().add(p1);
				}
				if (!StringUtils.isEmpty(entity.getVo_name())) {
					Predicate p2 = cb.equal(root.<String> get("vo_name"),  StringUtils.trimWhitespace(entity.getVo_name())  );
					predicate.getExpressions().add(p2);
				}
				if (!StringUtils.isEmpty(entity.getVo_extends())) {
					Predicate p3 = cb.equal(root.<String> get("vo_extends"),  StringUtils.trimWhitespace(entity.getVo_extends())  );
					predicate.getExpressions().add(p3);
				}
				if (!StringUtils.isEmpty(entity.getVo_path())) {
					Predicate p4 = cb.like(root.<String> get("vo_path"), "%"+ StringUtils.trimWhitespace(entity.getVo_path()) + "%" );
					predicate.getExpressions().add(p4);
				}
				if (!StringUtils.isEmpty(entity.getExtra_name())) {
					Predicate p5 = cb.equal(root.<String> get("extra_name"),  StringUtils.trimWhitespace(entity.getExtra_name())  );
					predicate.getExpressions().add(p5);
				}
				if (!StringUtils.isEmpty(entity.getExtra_def())) {
					Predicate p6 = cb.equal(root.<String> get("extra_def"),  StringUtils.trimWhitespace(entity.getExtra_def())  );
					predicate.getExpressions().add(p6);
				}
				if (!StringUtils.isEmpty(entity.getEo_name())) {
					Predicate p7 = cb.equal(root.<String> get("eo_name"),  StringUtils.trimWhitespace(entity.getEo_name())  );
					predicate.getExpressions().add(p7);
				}
				if (!StringUtils.isEmpty(entity.getEo_path())) {
					Predicate p8 = cb.like(root.<String> get("eo_path"),  "%" +StringUtils.trimWhitespace(entity.getEo_path()) +"%" );
					predicate.getExpressions().add(p8);
				}
				if (!StringUtils.isEmpty(entity.getVo_entity_usage_obj_def())) {
					Predicate p9 = cb.equal(root.<String> get("vo_entity_usage_obj_def"),  StringUtils.trimWhitespace(entity.getVo_entity_usage_obj_def())  );
					predicate.getExpressions().add(p9);
				}
				if (!StringUtils.isEmpty(entity.getDbo_type())) {
					Predicate p10 = cb.equal(root.<String> get("dbo_type"),  StringUtils.trimWhitespace(entity.getDbo_type())  );
					predicate.getExpressions().add(p10);
				}
				if (!StringUtils.isEmpty(entity.getDbo_name())) {
					Predicate p11 = cb.equal(root.<String> get("dbo_name"),  StringUtils.trimWhitespace(entity.getDbo_name())  );
					predicate.getExpressions().add(p11);
				}
				if (!StringUtils.isEmpty(entity.getDbo_col_name())) {
					Predicate p12 = cb.equal(root.<String> get("dbo_col_name"),  StringUtils.trimWhitespace(entity.getDbo_col_name())  );
					predicate.getExpressions().add(p12);
				}
				if (!StringUtils.isEmpty(entity.getDbo_path())) {
					Predicate p13 = cb.like(root.<String> get("dbo_path"),  "%"+StringUtils.trimWhitespace(entity.getDbo_path()) +"%" );
					predicate.getExpressions().add(p13);
				}
				if (!StringUtils.isEmpty(entity.getSdf_path())) {
					Predicate p14 = cb.like(root.<String> get("sdf_path"),  "%"+StringUtils.trimWhitespace(entity.getSdf_path()) +"%" );
					predicate.getExpressions().add(p14);
				}
				if (!StringUtils.isEmpty(entity.getSdf_vo())) {
					Predicate p15 = cb.equal(root.<String> get("sdf_vo"),  StringUtils.trimWhitespace(entity.getSdf_vo())  );
					predicate.getExpressions().add(p15);
				}
				if (!StringUtils.isEmpty(entity.getSdf_am())) {
					Predicate p16 = cb.equal(root.<String> get("sdf_am"),  StringUtils.trimWhitespace(entity.getSdf_am())  );
					predicate.getExpressions().add(p16);
				}
				if (!StringUtils.isEmpty(entity.getAm_name())) {
					Predicate p17 = cb.equal(root.<String> get("am_name"),  StringUtils.trimWhitespace(entity.getAm_name())  );
					predicate.getExpressions().add(p17);
				}
				if (!StringUtils.isEmpty(entity.getAm_vo_usage_name())) {
					Predicate p18 = cb.equal(root.<String> get("am_vo_usage_name"),  StringUtils.trimWhitespace(entity.getAm_vo_usage_name())  );
					predicate.getExpressions().add(p18);
				}
				if (!StringUtils.isEmpty(entity.getAm_vo_usage_object_def())) {
					Predicate p19 = cb.equal(root.<String> get("am_vo_usage_object_def"),  StringUtils.trimWhitespace(entity.getAm_vo_usage_object_def())  );
					predicate.getExpressions().add(p19);
				}
				return predicate;
			}
		});
		return businessObjectsRepository.findAll(equalspec);
	}

}
