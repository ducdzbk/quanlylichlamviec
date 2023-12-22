package com.fds.quanlilichlamviec.controller;

import com.fds.flex.common.ultility.RestfullUtil;
import com.fds.quanlilichlamviec.action.ThanhVienAction;
import com.fds.quanlilichlamviec.dto.req.ThanhVienReqDTO;
import com.fds.quanlilichlamviec.dto.resp.BaseRespDTO;
import com.fds.quanlilichlamviec.dto.resp.ExceptionRespDTO;
import com.fds.quanlilichlamviec.dto.resp.ThanhVienRespDTO;

import com.fds.quanlilichlamviec.entity.T_Model.ThanhVien;
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
// cho phép tên miền khác có thể truy cập vào miền này, ở đay "*" thì cho phép hết ko chạn j

@CrossOrigin(origins = "*")
@RestController
// kết nối giữa yêu cầu HTTP và CRUD, khi 1 chức năng của CRUD ợc tạo có đường dẫn
// như này thì nó sẽ dùng để xử lý yêu cầu từ HTTP
@RequestMapping("/v1/quanlylichlamviec/thanhvien")

// tên tài liệu API
@Api(value = "API CURD ThanhVien entity")
@Slf4j
public class ThanhVienController {
    @Autowired
    private ThanhVienAction thanhVienAction;

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.ThanhVien','UPDATE')")
    // consumes chỉ sử lí cá yêu cầu dưới dạng JSON hoặc PLAIN_TEXT
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Create ThanhVien collection", notes = "Create ThanhVien collection", response = ThanhVienRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = ThanhVienRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> addThanhVien(@RequestBody ThanhVienReqDTO request) {

        log.debug("Get body create ThanhVien: {} ", new JSONObject(request));

        ThanhVien thanhVien = thanhVienAction.addThanhVien(request);

        return ResponseEntity.ok(new ThanhVienRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", request, thanhVien));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.ThanhVien','UPDATE')")
    @PostMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Update ThanhVien entity", notes = "Update ThanhVien entity", response = ThanhVienRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = ThanhVienRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> updateThanhVien(@PathVariable(name = "id") String id, @RequestBody ThanhVienReqDTO request) {

        log.debug("Get body update ThanhVien: {} ", new JSONObject(request));

        ThanhVien ThanhVien = thanhVienAction.updateThanhVien(id, request);

        return ResponseEntity.ok(new ThanhVienRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(),
                "success", "success", request, ThanhVien));

    }

    @GetMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Find ThanhVien by Id", notes = "Find ThanhVien by Id", response = ThanhVienRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = ThanhVienRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> findThanhVien(@PathVariable(name = "id") String id) {

        log.debug("Find ThanhVien: {} ", id);

        ThanhVien thanhVien = thanhVienAction.findThanhVien(id);

        return ResponseEntity.ok(new ThanhVienRespDTO(RestfullUtil.RespCode.SUCCESS.getCode(),
                "success", "success", null, thanhVien));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.ThanhVien','DELETE')")
    @DeleteMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ApiOperation(value = "Delete ThanhVien by id", notes = "Delete ThanhVien by id", response = ThanhVienRespDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = ThanhVienRespDTO.class),
            @ApiResponse(code = 400, message = "Bad request error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 404, message = "Not found error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 409, message = "Conflic error", response = ExceptionRespDTO.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ExceptionRespDTO.class)})
    public ResponseEntity<?> deleteThanhVien(@PathVariable(name = "id") String id) {

        log.debug("Delete ThanhVien: {} ", id);

        thanhVienAction.deleteThanhVien(id);

        return ResponseEntity.ok(
                new BaseRespDTO<String, String>(RestfullUtil.RespCode.SUCCESS.getCode(), "success", "success", id, "Delete success"));

    }

    //@PreAuthorize("hasPermission('com.fds.flex.core.data.entity.C_Model.ThanhVien','READ')")
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

        log.debug("Filter ThanhVien: keyword: {}, page:{}, size:{}, orderFields:{}, orderTypes:{}", keyword, page, size,
                orderFields, orderTypes);

        Page<ThanhVien> result = thanhVienAction.filter(keyword, page, size, orderFields, orderTypes);

        return ResponseEntity.ok(result);

    }
}
