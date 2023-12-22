package com.fds.quanlilichlamviec.service;

import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoaiLichService {
    Optional<LoaiLich> findById(String id);
    Optional<LoaiLich> findByMaMuc(String maMuc);
    Optional<LoaiLich> finByMaNhom( String maNhom);
    Long countAll();
    void deleteLoaiLich(LoaiLich loaiLich);
    LoaiLich updateLoaiLich(LoaiLich loaiLich, Boolean isUpdate);
    Page<LoaiLich> filter(String keyword, Pageable pageable);
}
