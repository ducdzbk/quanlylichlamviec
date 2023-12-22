package com.fds.quanlilichlamviec.service.impl;

import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.constant.Constant;
import com.fds.quanlilichlamviec.entity.T_Model.LichNghi;
import com.fds.quanlilichlamviec.entity.T_Model.LichNghi;
import com.fds.quanlilichlamviec.repository.LichNghiRepository;
import com.fds.quanlilichlamviec.service.LichNghiService;
import com.fds.quanlilichlamviec.service.LichNghiService;
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
public class LichNghiServiceImpl implements LichNghiService {
    @Autowired
    private LichNghiRepository lichNghiRepository;
@Autowired
private MongoTemplate mongoTemplate;

    @Override
    public Optional<LichNghi> findById(String id) {
        return lichNghiRepository.findById(id);
    }

    @Override
    public Long countAll() {
        return lichNghiRepository.count();
    }


    @Override
    public void deleteLichNghi(LichNghi lichNghi) {
        lichNghiRepository.delete(lichNghi);
    }

    @Override
    public LichNghi updateLichNghi(LichNghi lichNghi, Boolean isUpdate) {
        return lichNghiRepository.save(lichNghi);
    }

    @Override
    public Page<LichNghi> filter(String keyword, Pageable pageable) {
        Query query = new Query().with(pageable);

        List<Criteria> criteria = new ArrayList<>();

        if (Validator.isNotNull(keyword)) {
            List<Criteria> subCriterias = new ArrayList<>();
            Criteria c = Criteria.where("TenLich").regex(toLikeKeyword(keyword), Constant.INSENSITIVE);
            subCriterias.add(c);
            c = Criteria.where("Nam").regex(toLikeKeyword(keyword), Constant.INSENSITIVE);
            subCriterias.add(c);
            criteria.add(new Criteria().orOperator(subCriterias));

        }
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria));
        }

        System.out.println(PageableExecutionUtils.getPage(mongoTemplate.find(query, LichNghi.class), pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), LichNghi.class)));
        return PageableExecutionUtils.getPage(mongoTemplate.find(query, LichNghi.class), pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), LichNghi.class));

    }
    private String toLikeKeyword(String source) {
        return source.replaceAll("\\*", ".*");
    }
    
}
