package com.fds.quanlilichlamviec.util;


import com.fds.flex.common.ultility.CompareObjectUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;

import com.fds.quanlilichlamviec.exception.InternalServerException;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author trungnt
 */
@Slf4j
public class QLLLVUtil {


    public static String increaseMaPhienBan(String maPhienBan) {
        Integer maPhienBanInt = 1;
        try {
            maPhienBanInt = Integer.parseInt(maPhienBan);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        maPhienBanInt++;
        return String.valueOf(maPhienBanInt);
    }

    public static Map<String, ?> convertListToMap(List<?> list) {
        if (Validator.isNull(list) || list.size() == 0) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        try {
            for (Object obj : list) {
                Object trangThaiDuLieu = obj.getClass().getField("trangThaiDuLieu").get(obj);
                String key = (String) trangThaiDuLieu.getClass().getField("maMuc").get(trangThaiDuLieu);
                map.put(key, obj);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalServerException(null, "cannot update data", StringPool.BLANK);
        }

        return map;
    }

    public static Sort sortBuilder(String orderFields, String orderTypes, Class<?> clazz) {

        if (Validator.isNull(orderFields) || orderFields.length() == 0) {
            return null;
        }

        List<Order> orders = new ArrayList<Order>();

        String[] fields = StringUtil.split(orderFields);
        String[] types = StringUtil.split(orderTypes);

        List<Boolean> orderLst = new ArrayList<Boolean>();

        if (types != null) {
            for (String type : types) {
                if (Validator.isNull(type)) {
                    orderLst.add(true);
                } else {
                    if (type.equalsIgnoreCase("desc")) {
                        orderLst.add(false);
                    } else {
                        orderLst.add(true);
                    }
                }
            }
        }

        if (Validator.isNotNull(orderFields)) {
            int index = 0;
            for (String fieldName : fields) {
                try {
                    Field field = clazz.getField(fieldName);
                    org.springframework.data.mongodb.core.mapping.Field anotation = field
                            .getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                    if (anotation != null) {
                        String name = anotation.value();

                        if (Validator.isNull(name)) {
                            continue;
                        }
                        if (orderLst.size() - 1 >= index) {
                            boolean isAsc = orderLst.get(index);
                            Order order = isAsc ? (Order.asc(name)) : (Order.desc(name));
                            orders.add(order);
                        }
                    }
                } catch (Exception e) {
//                    log.error(e.getStackTrace()[0].getClassName() + "|" + e.getStackTrace()[0].getMethodName() + "|"
//                            + "can not get field by name:{}", fieldName);
                     e.printStackTrace();
                }

                index++;
            }
        }
        return Sort.by(orders);
    }
}