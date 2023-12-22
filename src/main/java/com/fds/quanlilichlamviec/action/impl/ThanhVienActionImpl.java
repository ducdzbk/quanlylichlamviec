package com.fds.quanlilichlamviec.action.impl;

import com.fds.flex.common.ultility.RestfullUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.action.ThanhVienAction;
import com.fds.quanlilichlamviec.constant.Constant;
import com.fds.quanlilichlamviec.constant.NotificationConstant;
import com.fds.quanlilichlamviec.dto.req.ThanhVienReqDTO;

import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
import com.fds.quanlilichlamviec.exception.BadRequestException;
import com.fds.quanlilichlamviec.exception.ConflicException;
import com.fds.quanlilichlamviec.exception.NotfoundException;
import com.fds.quanlilichlamviec.service.ThanhVienService;
import com.fds.quanlilichlamviec.util.MessageUtil;
import com.fds.quanlilichlamviec.util.QLLLVUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ThanhVienActionImpl implements ThanhVienAction {

    @Autowired
    private ThanhVienService thanhVienService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ThanhVien findThanhVien(String id) {
        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    NotificationConstant.THANHVIEN_ID_NULL_EMPTY,
                    MessageUtil.responseMessage(NotificationConstant.THANHVIEN_ID_NULL_EMPTY));
        }
        Optional<ThanhVien> thanhVienOptional = thanhVienService.findById(id);
        if (!thanhVienOptional.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    NotificationConstant.THANHVIEN_DOCUMENT_NOT_FOUND,
                    MessageUtil.responseMessage(NotificationConstant.THANHVIEN_DOCUMENT_NOT_FOUND));
        }
        return thanhVienOptional.get();
    }

    @Override
    public ThanhVien addThanhVien(ThanhVienReqDTO thanhVienReqDTO) {
        ThanhVien thanhVien = new ThanhVien(false);

        modelMapper.map(thanhVienReqDTO, thanhVien);



        // TODO set NguoiTaoLap set NguoiCapNhat

        return thanhVienService.updateThanhVien(thanhVien, false);
    }

    @Override
    public ThanhVien updateThanhVien(String id, ThanhVienReqDTO thanhVienReqDTO) {

        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    "thanhVien.id_null_or_empty", RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getMsg());
        }

        Optional<ThanhVien> thanhVienOpt = thanhVienService.findById(id);

        if (!thanhVienOpt.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    "thanhVien_notfound_entry", RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getMsg());
        }

        ThanhVien thanhVien = thanhVienOpt.get();

        modelMapper.map(thanhVienReqDTO, thanhVien);



        // TODO set NguoiTaoLap set NguoiCapNhat

        return thanhVienService.updateThanhVien(thanhVien, true);
    }


    @Override
    public void deleteThanhVien(String id) {
        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    "thanhVien.id_null_or_empty", RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getMsg());
        }

        Optional<ThanhVien> thanhVienOpt = thanhVienService.findById(id);

        if (!thanhVienOpt.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    "thanhVien_notfound_entry", RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getMsg());
        }

        thanhVienService.deleteThanhVien(thanhVienOpt.get());
    }


    @Override
    public Page<ThanhVien> filter(String keyword, Integer page, Integer size, String orderFields, String orderTypes) {
        if (page == -1 && size == -1) {
            page = 0;
            size = thanhVienService.countAll().intValue() > Constant.DEFAULT_MAX_PAGE_SIZE ? thanhVienService.countAll().intValue() : Constant.DEFAULT_MAX_PAGE_SIZE;
        } else {
            if (page == null || page < 0) {
                page = 0;
            }

            if (size == null || size < 0) {
                size = Constant.DEFAULT_MIN_PAGE_SIZE;
            }


            if (size > Constant.DEFAULT_MAX_PAGE_SIZE) {
                size = Constant.DEFAULT_MAX_PAGE_SIZE;
            }
        }

        Sort sort = QLLLVUtil.sortBuilder(orderFields, orderTypes, ThanhVien.class);

        Pageable pageable = PageRequest.of(page, size, sort);

        return thanhVienService.filter(keyword, pageable);
    }
}

