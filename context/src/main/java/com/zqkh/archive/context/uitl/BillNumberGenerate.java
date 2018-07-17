package com.zqkh.archive.context.uitl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hty
 * @create 2017-12-29 14:46
 **/

public class BillNumberGenerate {
    private static int num = 1;
    private static String date;

    private BillNumberGenerate() {
    }

    /**
     * 生成订单编号
     *
     * @return
     */
    public static synchronized String getBillNo() {
        String str = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        if (str.equals(date)) {
            num += 1;
        }else{
            date = str;
            num = 1;
        }
        long billNo = Long.parseLong(date) * 10000;
        billNo += num;
        return billNo + "";
    }
}