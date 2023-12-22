package com.fds.quanlilichlamviec.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtil {

    public static String responseMessage(String msgKey) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language", new Locale("en_US"));
        if (resourceBundle.containsKey(msgKey)) {
            msgKey = resourceBundle.getString(msgKey);
        }

        return msgKey;
    }

    public static String responseMessage(String errorCode, String msgKey, ArrayNode data) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language", new Locale("vi_VN"));
        if (resourceBundle.containsKey(msgKey)) {
            msgKey = resourceBundle.getString(msgKey);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode msg = objectMapper.createObjectNode();
        msg.put("error", errorCode);
        msg.put("message", msgKey);
        msg.set("data", data);
        return msg.toString();
    }
}

