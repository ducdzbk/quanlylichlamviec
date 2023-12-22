package com.fds.quanlilichlamviec.controller;

import com.fds.flex.common.ultility.RestfullUtil;
import com.fds.quanlilichlamviec.action.LoaiLichAction;
import com.fds.quanlilichlamviec.dto.req.LoaiLichReqDTO;
import com.fds.quanlilichlamviec.dto.resp.BaseRespDTO;
import com.fds.quanlilichlamviec.dto.resp.ExceptionRespDTO;
import com.fds.quanlilichlamviec.dto.resp.LoaiLichRespDTO;
import com.fds.quanlilichlamviec.entity.C_Model.LoaiLich;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author vietdd
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/quanlylichlamviec/loailich")
@Api(value = "API CURD LoaiLich entity")
@Slf4j
public class LoaiLichController {
    @Autowired
    private LoaiLichAction loaiLichAction;

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.LoaiLich','UPDATE')")
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Create LoaiLich collection", notes = "Create LoaiLich collection", response = LoaiLichRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = LoaiLichRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> addLoaiLich(@RequestBody LoaiLichReqDTO request) {

        log.debug("Get body create LoaiLich: {} ", new JSONObject(request));

        LoaiLich loaiLich = loaiLichAction.addLoaiLich(request);

        return ResponseEntity.ok(new LoaiLichRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", request, loaiLich));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.LoaiLich','UPDATE')")
    @PostMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Update LoaiLich entity", notes = "Update LoaiLich entity", response = LoaiLichRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = LoaiLichRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> updateLoaiLich(@PathVariable(name = "id") String id, @RequestBody LoaiLichReqDTO request) {

        log.debug("Get body update LoaiLich: {} ", new JSONObject(request));

        LoaiLich LoaiLich = loaiLichAction.updateLoaiLich(id, request);

        return ResponseEntity.ok(new LoaiLichRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", request, LoaiLich));

    }

    @GetMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Find LoaiLich by Id", notes = "Find LoaiLich by Id", response = LoaiLichRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = LoaiLichRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> findLoaiLich(@PathVariable(name = "id") String id) {

        log.debug("Find LoaiLich: {} ", id);

        LoaiLich loaiLich = loaiLichAction.findLoaiLich(id);

        return ResponseEntity.ok(new LoaiLichRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", null, loaiLich));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.LoaiLich','DELETE')")
    @DeleteMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Delete LoaiLich by id", notes = "Delete LoaiLich by id", response = LoaiLichRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = LoaiLichRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> deleteLoaiLich(@PathVariable(name = "id") String id) {

        log.debug("Delete LoaiLich: {} ", id);

        loaiLichAction.deleteLoaiLich(id);

        return ResponseEntity.ok(
                new BaseRespDTO<String, String>(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", id, "Delete success"));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.LoaiLich','READ')")
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

        log.debug("Filter LoaiLich: keyword: {}, page:{}, size:{}, orderFields:{}, orderTypes:{}", keyword, page, size,
                orderFields, orderTypes);

        Page<LoaiLich> result = loaiLichAction.filter(keyword, page, size, orderFields, orderTypes);

        return ResponseEntity.ok(result);

    }
}
