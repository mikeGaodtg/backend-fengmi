package com.qf.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtils {
    public static String createOrderId(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Random r = new Random();
        int i = r.nextInt(900)+100;
        return simpleDateFormat.format(new Date())+i;
    }
}
