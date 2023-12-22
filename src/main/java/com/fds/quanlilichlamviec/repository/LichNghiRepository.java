package com.fds.quanlilichlamviec.repository;

import com.fds.quanlilichlamviec.entity.T_Model.LichNghi;
import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LichNghiRepository extends MongoRepository<LichNghi, String> {
    Optional<LichNghi> findById(String id);
}
