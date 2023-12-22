package com.fds.quanlilichlamviec.service.impl;

import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.constant.Constant;
import com.fds.quanlilichlamviec.context.ServiceContext;
import com.fds.quanlilichlamviec.context.ServiceContextHolder;
import com.fds.quanlilichlamviec.entity.T_Model.LichLamViecCaNhan;
import com.fds.quanlilichlamviec.model.UserInfoModel;
import com.fds.quanlilichlamviec.repository.LichLamViecCaNhanRepository;
import com.fds.quanlilichlamviec.service.LichLamViecCaNhanService;
import org.json.JSONObject;
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
public class LichLamViecCaNhanServiceImpl implements LichLamViecCaNhanService {
    @Autowired
    private LichLamViecCaNhanRepository lichLamViecCaNhanRepository;
@Autowired
private MongoTemplate mongoTemplate;

    @Override
    public Optional<LichLamViecCaNhan> findById(String id) {
        return lichLamViecCaNhanRepository.findById(id);
    }

    @Override
    public Long countAll() {
        return lichLamViecCaNhanRepository.count();
    }

    @Override
    public void deleteLichLamViecCaNhan(LichLamViecCaNhan lichLamViecCaNhan) {
        lichLamViecCaNhanRepository.delete(lichLamViecCaNhan);
    }

    @Override
    public LichLamViecCaNhan updateLichLamViecCaNhan(LichLamViecCaNhan lichLamViecCaNhan, Boolean isUpdate) {
        return lichLamViecCaNhanRepository.save(lichLamViecCaNhan);
    }

    @Override
    public Page<LichLamViecCaNhan> filter(String keyword, Pageable pageable) {
        Query query = new Query().with(pageable);

        List<Criteria> criteria = new ArrayList<>();

        ServiceContext serviceContext = ServiceContextHolder.getContext();
        UserInfoModel userInfoModel = serviceContext.getUserInfoModel();

        if (Validator.isNotNull(keyword)) {
            List<Criteria> subCriterias = new ArrayList<>();
            Criteria c = Criteria.where("TieuDe").regex(toLikeKeyword(keyword), Constant.INSENSITIVE);
            subCriterias.add(c);
            c = Criteria.where("LoaiLich").regex(toLikeKeyword(keyword), Constant.INSENSITIVE);
            subCriterias.add(c);
            criteria.add(new Criteria().orOperator(subCriterias));

        }

        //Check người tạo lập
        Criteria c = Criteria.where("NguoiTaoLap.MaDinhDanh").is(userInfoModel.getThanhVien().getMaDinhDanh());
        Criteria c_chiaSe = Criteria.where("ChiaSe.MaDinhDanh").is(userInfoModel.getThanhVien().getMaDinhDanh());

        List<Criteria> checkQuyen = new ArrayList<>();
        checkQuyen.add(c);
        checkQuyen.add(c_chiaSe);
        criteria.add(new Criteria().orOperator(checkQuyen));

        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria));
        }
        System.out.println(new JSONObject(query));
        return PageableExecutionUtils.getPage(mongoTemplate.find(query, LichLamViecCaNhan.class), pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), LichLamViecCaNhan.class));

    }
    private String toLikeKeyword(String source) {
        return source.replaceAll("\\*", ".*");
    }

}
