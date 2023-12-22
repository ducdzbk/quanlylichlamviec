package com.fds.quanlilichlamviec.service;

import com.fds.quanlilichlamviec.entity.T_Model.LichNghi;
import com.fds.quanlilichlamviec.entity.T_Model.LichNghi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LichNghiService {
    Optional<LichNghi> findById(String id);
    Long countAll();
    void deleteLichNghi(LichNghi LichNghi);
    LichNghi updateLichNghi(LichNghi LichNghi, Boolean isUpdate);
    Page<LichNghi> filter(String keyword, Pageable pageable);
}
