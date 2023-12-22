package com.fds.quanlilichlamviec.service;

import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ThanhVienService {
    Optional<ThanhVien> findById(String id);
    Optional<ThanhVien> findByUsername(String username);


    Long countAll();
    void deleteThanhVien(ThanhVien thanhVien);
    ThanhVien updateThanhVien(ThanhVien thanhVien, Boolean isUpdate);
    Page<ThanhVien> filter(String keyword, Pageable pageable);
}
