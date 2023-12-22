package com.fds.quanlilichlamviec.util;

import com.fds.quanlilichlamviec.constant.StringPool;
import org.modelmapper.internal.bytebuddy.utility.RandomString;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UuidGenerator {
    public static String random(Class classObj) {
        UUID uuid = UUID.randomUUID();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String className = UuidGenerator.getUppercaseString(classObj.getSimpleName());
        return uuid.toString() + StringPool.DASH + new RandomString(16).nextString().toLowerCase() + StringPool.DASH + timestamp.getTime() + StringPool.DASH +className;
    }
    public static String getUppercaseString(String input){
        //
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(input);
        String result = StringPool.BLANK;
        while (matcher.find()) {
            result += matcher.group();
        }
        return result;
    }
}
