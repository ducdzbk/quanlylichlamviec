package com.fds.quanlilichlamviec.controller;

import com.fds.flex.common.ultility.RestfullUtil;

import com.fds.quanlilichlamviec.action.NhomAction;
import com.fds.quanlilichlamviec.dto.req.NhomReqDTO;

import com.fds.quanlilichlamviec.dto.resp.BaseRespDTO;
import com.fds.quanlilichlamviec.dto.resp.ExceptionRespDTO;
import com.fds.quanlilichlamviec.dto.resp.NhomRespDTO;

import com.fds.quanlilichlamviec.entity.T_Model.Nhom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author vietdd
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/quanlylichlamviec/nhom")
@Api(value = "API CURD nhom entity")
@Slf4j
public class NhomController {
    @Autowired
    private NhomAction nhomAction;
 

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.nhom','UPDATE')")
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Create nhom collection", notes = "Create nhom collection", response = NhomRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = NhomRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> addnhom(@RequestBody NhomReqDTO request) {

        log.debug("Get body create nhom: {} ", new JSONObject(request));

        Nhom nhom = nhomAction.addNhom(request);

        return ResponseEntity.ok(new NhomRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", request, nhom));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.nhom','UPDATE')")
    @PostMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Update nhom entity", notes = "Update nhom entity", response = NhomRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = NhomRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> updatenhom(@PathVariable(name = "id") String id, @RequestBody NhomReqDTO request) {

        log.debug("Get body update nhom: {} ", new JSONObject(request));

        Nhom nhom = nhomAction.updateNhom(id,request);

        return ResponseEntity.ok(new NhomRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", request, nhom));

    }

    @GetMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Find nhom by Id", notes = "Find nhom by Id", response = NhomRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = NhomRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> findnhom(@PathVariable(name = "id") String id) {

        log.debug("Find nhom: {} ", id);

        Nhom nhom = nhomAction.findNhom(id);

        return ResponseEntity.ok(new NhomRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", null, nhom));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.nhom','DELETE')")
    @DeleteMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Delete nhom by id", notes = "Delete nhom by id", response = NhomRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = NhomRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> deletenhom(@PathVariable(name = "id") String id) {

        log.debug("Delete nhom: {} ", id);

        nhomAction.deleteNhom(id);

        return ResponseEntity.ok(
                new BaseRespDTO<String, String>(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", id, "Delete success"));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.nhom','READ')")
    @GetMapping(value = "/filter", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Filter", notes = "Filter", response = Page.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Page.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> filter(@RequestParam(name = "keyword", required = false) String keyword,
                                    @RequestParam(name = "thamChieu_maMuc", required = false) String thamChieu_maMuc,
                                    @RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size,
                                    @RequestParam(name = "orderFields", required = false, defaultValue = "MaMuc") String orderFields,
                                    @RequestParam(name = "orderTypes", required = false, defaultValue = "asc") String orderTypes) {

        log.debug("Filter nhom: keyword: {}, page:{}, size:{}, orderFields:{}, orderTypes:{}", keyword, page, size,
                orderFields, orderTypes);

        Page<Nhom> result = nhomAction.filter(keyword, page, size, orderFields, orderTypes);

        return ResponseEntity.ok(result);

    }
}
