package com.fds.quanlilichlamviec.repository;

import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;
import com.fds.quanlilichlamviec.entity.T_Model.Nhom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface NhomRepository extends MongoRepository<Nhom, String> {
    Optional<Nhom> findById(String id);

    Optional<Nhom> findByMaDinhDanh(String maDinhDanh);


}
