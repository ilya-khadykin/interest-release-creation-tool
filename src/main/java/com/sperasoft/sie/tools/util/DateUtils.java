package com.sperasoft.sie.tools.util;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Ilya K on 7/16/2017.
 */
public class DateUtils {

    public static final String ISO_8601_24H_FULL_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static String convertDate(ZonedDateTime date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}
