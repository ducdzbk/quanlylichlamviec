package com.fds.quanlilichlamviec.service;


import com.fds.quanlilichlamviec.entity.T_Model.Nhom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NhomService {
    Optional<Nhom> findById(String id);

    Optional<Nhom> findByMaDinhDanh(String maDinhDanh);

    Optional<Nhom> findByMaNhom(String maNhom);

    Long countAll();
    void deletenhom(Nhom nhom);
    Nhom updatenhom(Nhom nhom, Boolean isUpdate);
    Page<Nhom> filter(String keyword, Pageable pageable);
}
