package com.fds.quanlilichlamviec.service.impl;

import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.constant.Constant;
import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;
import com.fds.quanlilichlamviec.entity.T_Model.Nhom;
import com.fds.quanlilichlamviec.repository.LoaiLichRepository;
import com.fds.quanlilichlamviec.service.LoaiLichService;
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
public class LoaiLichServiceImpl implements LoaiLichService {
    @Autowired
    private LoaiLichRepository loaiLichRepository;
@Autowired
private MongoTemplate mongoTemplate;

    @Override
    public Optional<LoaiLich> findById(String id) {
        return loaiLichRepository.findById(id);
    }

    @Override
    public Optional<LoaiLich> findByMaMuc(String maMuc) {
        return loaiLichRepository.findByMaMuc( maMuc);
    }

    @Override
    public Optional<LoaiLich> finByMaNhom(String maNhom) {
        return loaiLichRepository.findByMaNhom(maNhom);
    }

    @Override
    public Long countAll() {
        return loaiLichRepository.count();
    }

    @Override
    public void deleteLoaiLich(LoaiLich loaiLich) {
        loaiLichRepository.delete(loaiLich);
    }

    @Override
    public LoaiLich updateLoaiLich(LoaiLich loaiLich, Boolean isUpdate) {
        return loaiLichRepository.save(loaiLich);
    }

    @Override
    public Page<LoaiLich> filter(String keyword, Pageable pageable) {
        Query query = new Query().with(pageable);

        List<Criteria> criteria = new ArrayList<>();

        if (Validator.isNotNull(keyword)) {
            List<Criteria> subCriterias = new ArrayList<>();
            Criteria c = Criteria.where("TenMuc").regex(toLikeKeyword(keyword), Constant.INSENSITIVE);
            subCriterias.add(c);
            c = Criteria.where("MaMuc").regex(toLikeKeyword(keyword), Constant.INSENSITIVE);
            subCriterias.add(c);
            criteria.add(new Criteria().orOperator(subCriterias));

        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria));
        }
        return PageableExecutionUtils.getPage(mongoTemplate.find(query, LoaiLich.class), pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), LoaiLich.class));

    }
    private String toLikeKeyword(String source) {
        return source.replaceAll("\\*", ".*");
    }

}
