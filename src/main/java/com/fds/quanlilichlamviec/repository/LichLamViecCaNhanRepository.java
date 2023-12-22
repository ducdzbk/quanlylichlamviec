package com.fds.quanlilichlamviec.repository;

import com.fds.quanlilichlamviec.entity.T_Model.LichLamViecCaNhan;
import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface LichLamViecCaNhanRepository extends MongoRepository<LichLamViecCaNhan, String> {
    Optional<LichLamViecCaNhan> findById(String id);
}
