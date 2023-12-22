package com.fds.quanlilichlamviec.service.impl;


import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.constant.Constant;
import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;
import com.fds.quanlilichlamviec.entity.T_Model.Nhom;
import com.fds.quanlilichlamviec.repository.NhomRepository;
import com.fds.quanlilichlamviec.service.NhomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NhomServiceImpl implements NhomService {
    @Autowired
    NhomRepository nhomRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<Nhom> findById(String id) {
        return nhomRepository.findById(id);
    }

    @Override
    public Optional<Nhom> findByMaDinhDanh(String maDinhDanh) {
        return nhomRepository.findByMaDinhDanh(maDinhDanh);
    }

    @Override
    public Optional<Nhom> findByMaNhom(String maNhom) {
        return Optional.empty();
    }


    @Override
    public Long countAll() {
        return nhomRepository.count();
    }

    @Override
    public void deletenhom(Nhom nhom) {
        nhomRepository.delete(nhom);
    }

    @Override
    public Nhom updatenhom(Nhom nhom, Boolean isUpdate) {
        return nhomRepository.save(nhom);
    }

    @Override
    public Page<Nhom> filter(String keyword, Pageable pageable) {
        Query query = new Query().with(pageable);

        List<Criteria> criteria = new ArrayList<>();

        if (Validator.isNotNull(keyword)) {
            List<Criteria> subCriterias = new ArrayList<>();
            Criteria c = Criteria.where("TenNhom").regex(toLikeKeyword(keyword), Constant.INSENSITIVE);
            subCriterias.add(c);
            c = Criteria.where("MaNhom").regex(toLikeKeyword(keyword), Constant.INSENSITIVE);
            subCriterias.add(c);
            criteria.add(new Criteria().orOperator(subCriterias));

        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria));
        }
        return PageableExecutionUtils.getPage(mongoTemplate.find(query, Nhom.class), pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Nhom.class));

    }

    private String toLikeKeyword(String source) {
        return source.replaceAll("\\*", ".*");
    }
}


