package com.fds.quanlilichlamviec.repository;

import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ThanhVienRepository extends MongoRepository<ThanhVien, String> {
    Optional<ThanhVien> findById(String id);
    Optional<ThanhVien> findByUsername(String username);
}
