package com.fds.quanlilichlamviec;

import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.constant.Constant;
import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import com.fds.quanlilichlamviec.util.QLLLVUtil;
import com.fds.quanlilichlamviec.util.UuidGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;


public class Test {
    public static void main(String[] args) {
//        String keyword = "123";
//        Query query = new Query();
//
//        List<Criteria> criteria = new ArrayList<>();
//
//        if (Validator.isNotNull(keyword)) {
//            Criteria c = Criteria.where("VaiTro.MaDinhDanh").regex(keyword);
//            criteria.add(c);
//        }
//        if (!criteria.isEmpty()) {
//            query.addCriteria(new Criteria().andOperator(criteria));
//        }
//        System.out.println(query.toString());

//        String input = "HoVaTen,ThuDienTu";
//        String inputType = "ASC,DESC";
//        QLLLVUtil.sortBuilder(input, inputType, ThanhVien.class);
        UuidGenerator.random(ThanhVien.class);
        System.out.println( UuidGenerator.random(ThanhVien.class));
    }
    private static String toLikeKeyword(String source) {
        return source.replaceAll("\\*", ".*");
    }
}