package com.fds.quanlilichlamviec.service.impl;

import com.fds.flex.common.ultility.RestfullUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.constant.Constant;
import com.fds.quanlilichlamviec.constant.NotificationConstant;
import com.fds.quanlilichlamviec.context.ServiceContext;
import com.fds.quanlilichlamviec.context.ServiceContextHolder;
import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import com.fds.quanlilichlamviec.exception.UnauthorizedException;
import com.fds.quanlilichlamviec.model.UserInfoModel;
import com.fds.quanlilichlamviec.repository.ThanhVienRepository;
import com.fds.quanlilichlamviec.service.ThanhVienService;
import com.fds.quanlilichlamviec.util.MessageUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThanhVienServiceImpl implements ThanhVienService {
    @Autowired
    private ThanhVienRepository thanhVienRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<ThanhVien> findById(String id) {
        return thanhVienRepository.findById(id);
    }

    @Override
    public Optional<ThanhVien> findByUsername(String username) {
        return thanhVienRepository.findByUsername(username);
    }


    @Override
    public Long countAll() {
        return thanhVienRepository.count();
    }

    @Override
    public void deleteThanhVien(ThanhVien thanhVien) {
        thanhVienRepository.delete(thanhVien);
    }

    @Override
    public ThanhVien updateThanhVien(ThanhVien thanhVien, Boolean isUpdate) {
        return thanhVienRepository.save(thanhVien);
    }

    @Override
    public Page<ThanhVien> filter(String keyword, Pageable pageable) {
        Query query = new Query().with(pageable);

        List<Criteria> criteria = new ArrayList<>();



        if (Validator.isNotNull(keyword)) {
            List<Criteria> subCriterias = new ArrayList<>();
            Criteria c = Criteria.where("HoVaTen").regex(toLikeKeyword(keyword), Constant.INSENSITIVE);
            subCriterias.add(c);
            c = Criteria.where("ThuDienTu").regex(toLikeKeyword(keyword), Constant.INSENSITIVE);
            subCriterias.add(c);
            criteria.add(new Criteria().orOperator(subCriterias));
        }



        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria));
        }

        return PageableExecutionUtils.getPage(mongoTemplate.find(query, ThanhVien.class), pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), ThanhVien.class));

    }

    private String toLikeKeyword(String source) {
        return source.replaceAll("\\*", ".*");
    }

}
