package com.fds.quanlilichlamviec.util;


import com.fds.flex.common.ultility.Validator;
import com.fds.quanlilichlamviec.constant.StringPool;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static final String _VN_TIME_ZONE_ = "Asia/Ho_Chi_Minh";
    public static final String _GLOBAL_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String _VN_DATE_FORMAT = "dd/MM/yyyy";

    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    public static LocalDate getLocalDate(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }

    public static Integer compareYear(Date date1, Date date2) {
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year_localDate1 = localDate1.getYear();
        int year_localDate2 = localDate2.getYear();
        if (year_localDate1 > year_localDate2) {
            return 1;
        }
        if (year_localDate1 < year_localDate2) {
            return -1;
        }
        if (year_localDate1 == year_localDate2) {
            return 0;
        }
        return null;
    }

    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    public static int getYear(LocalDate localDate) {

        if (localDate == null) {
            return -1;
        }
        return localDate.getYear();
    }

    public static int getMonth(LocalDate localDate) {

        if (localDate == null) {
            return -1;
        }
        return localDate.getMonthValue();
    }

    public static int getDate(LocalDate localDate) {

        if (localDate == null) {
            return -1;
        }
        return localDate.getDayOfMonth();
    }

    public static int getLengthOfMonth(LocalDate localDate) {
        if (localDate == null) {
            return -1;
        }
        return localDate.lengthOfMonth();
    }

    public static String dateToString(Date date, String pattern) {

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        if (Validator.isNull(date) || Validator.isNull(pattern)) {
            return StringPool.BLANK;
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return formatter.format(calendar.getTime());
    }

    public static LocalDate convertStringToDateAPI(String strDate, String pattern) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = null;

        if (formatter != null && Validator.isNotNull(strDate)) {
            date = LocalDate.parse(strDate, formatter);
        }


        return date;
    }
}