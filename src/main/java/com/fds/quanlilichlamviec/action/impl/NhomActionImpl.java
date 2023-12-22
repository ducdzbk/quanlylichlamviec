package com.fds.quanlilichlamviec.action.impl;

import com.fds.flex.common.ultility.RestfullUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.action.NhomAction;
import com.fds.quanlilichlamviec.constant.Constant;
import com.fds.quanlilichlamviec.constant.NotificationConstant;
import com.fds.quanlilichlamviec.dto.req.NhomReqDTO;

import com.fds.quanlilichlamviec.entity.ExtraModel.Nhom_ThanhVien;
import com.fds.quanlilichlamviec.entity.T_Model.Nhom;
import com.fds.quanlilichlamviec.exception.BadRequestException;
import com.fds.quanlilichlamviec.exception.ConflicException;
import com.fds.quanlilichlamviec.exception.NotfoundException;
import com.fds.quanlilichlamviec.service.NhomService;
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
public class NhomActionImpl implements NhomAction {

    @Autowired
    private NhomService nhomService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Nhom findNhom(String id) {
        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    NotificationConstant.Nhom_ID_NULL_EMPTY,
                    MessageUtil.responseMessage(NotificationConstant.Nhom_ID_NULL_EMPTY));
        }
        Optional<Nhom> NhomOptional = nhomService.findById(id);
        if (!NhomOptional.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    NotificationConstant.NHOM_DOCUMENT_NOT_FOUND,
                    MessageUtil.responseMessage(NotificationConstant.NHOM_DOCUMENT_NOT_FOUND));
        }
        return NhomOptional.get();
    }

    @Override
    public Nhom addNhom(NhomReqDTO NhomReqDTO) {
        Nhom nhom = new Nhom(false);

        modelMapper.map(NhomReqDTO, nhom);

        Optional<Nhom> NhomOpt = nhomService.findByMaNhom(nhom.maNhom);

        if (NhomOpt.isPresent()) {
            throw new ConflicException(RestfullUtil.RespCode.CONFLIC_ERROR.getCode(), "Nhom.manhom_conflic",
                    RestfullUtil.RespCode.CONFLIC_ERROR.getMsg());
        }

        // TODO set NguoiTaoLap set NguoiCapNhat

        return nhomService.updatenhom(nhom, false);
    }

    @Override
    public Nhom updateNhom(String id, NhomReqDTO NhomReqDTO) {

        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    "Nhom.id_null_or_empty", RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getMsg());
        }

        Optional<Nhom> NhomOpt = nhomService.findById(id);

        if (!NhomOpt.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    "Nhom_notfound_entry", RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getMsg());
        }

        Nhom nhom = NhomOpt.get();

        modelMapper.map(NhomReqDTO, nhom);

        NhomOpt = nhomService.findByMaNhom(nhom.maNhom);

        if (NhomOpt.isPresent() && !NhomOpt.get().get_id().toHexString().equals(id)) {
            throw new ConflicException(RestfullUtil.RespCode.CONFLIC_ERROR.getCode(), "Nhom.mamuc_conflic",
                    RestfullUtil.RespCode.CONFLIC_ERROR.getMsg());
        }

        // TODO set NguoiTaoLap set NguoiCapNhat

        return nhomService.updatenhom(nhom, true);
    }


    @Override
    public void deleteNhom(String id) {
        if (Validator.isNull(id)) {
            throw new BadRequestException(RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getCode(),
                    "Nhom.id_null_or_empty", RestfullUtil.RespCode.FIELD_NULL_OR_EMPRY_ERROR.getMsg());
        }

        Optional<Nhom> nhomOpt = nhomService.findById(id);

        if (!nhomOpt.isPresent()) {
            throw new NotfoundException(RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getCode(),
                    "Nhom_notfound_entry", RestfullUtil.RespCode.NOT_FOUND_ENTRY_ERROR.getMsg());
        }

        nhomService.deletenhom(nhomOpt.get());
    }


    @Override
    public Page<Nhom> filter(String keyword, Integer page, Integer size, String orderFields, String orderTypes) {
        if (page == -1 && size == -1) {
            page = 0;
            size = nhomService.countAll().intValue() > Constant.DEFAULT_MAX_PAGE_SIZE ? nhomService.countAll().intValue() : Constant.DEFAULT_MAX_PAGE_SIZE;
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

        Sort sort = QLLLVUtil.sortBuilder(orderFields, orderTypes, Nhom.class);

        Pageable pageable = PageRequest.of(page, size, sort);

        return nhomService.filter(keyword, pageable);
    }
}

