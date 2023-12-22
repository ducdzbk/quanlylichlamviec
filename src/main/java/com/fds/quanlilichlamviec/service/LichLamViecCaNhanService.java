package com.fds.quanlilichlamviec.service;

import com.fds.quanlilichlamviec.entity.T_Model.LichLamViecCaNhan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LichLamViecCaNhanService {
    Optional<LichLamViecCaNhan> findById(String id);
    Long countAll();
    void deleteLichLamViecCaNhan(LichLamViecCaNhan lichLamViecCaNhan);
    LichLamViecCaNhan updateLichLamViecCaNhan(LichLamViecCaNhan lichLamViecCaNhan, Boolean isUpdate);
    Page<LichLamViecCaNhan> filter(String keyword, Pageable pageable);
}
