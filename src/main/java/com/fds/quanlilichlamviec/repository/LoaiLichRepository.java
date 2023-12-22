package com.fds.quanlilichlamviec.repository;

import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;
import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface LoaiLichRepository extends MongoRepository<LoaiLich, String> {
    Optional<LoaiLich> findById(String id);
    Optional<LoaiLich> findByMaMuc(String maMuc);
    Optional<LoaiLich> findByMaNhom(String maNhom);
}
