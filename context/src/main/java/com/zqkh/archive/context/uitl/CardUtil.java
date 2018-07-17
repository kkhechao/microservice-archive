package com.zqkh.archive.context.uitl;

/**
 * 身份证信息算法类 
 *  
 * @author javaweb 
 *  
 */  
public class CardUtil {

    public static final int BIRTHDAY_BEGIN_INDEX = 6;
    public static final int BIRTHDAY_END_INDEX = BIRTHDAY_BEGIN_INDEX + 8;

    /**
     * 根据身份证的号码算出当前身份证持有者的性别和年龄 18位身份证 
     *  
     * @return 
     * @throws Exception 
     */  
    public static Boolean isGirl(String cardCode) {
        return Integer.parseInt(cardCode.substring(16).substring(0, 1)) % 2 == 0;
    }

    /**
     * 根据身份证号码获取生日
     * @param cardCode
     * @return
     */
    public static String getBirthday(String cardCode){
        return cardCode.substring(BIRTHDAY_BEGIN_INDEX,  BIRTHDAY_END_INDEX);
    }

    public static String getBirthdayFormat(String cardCode){
        StringBuilder stringBuilder = new StringBuilder();
        String birthday = getBirthday(cardCode);
        String year = birthday.substring(0, 4);
        String month = birthday.substring(4, 6);
        String day = birthday.substring(6, 8);
        return stringBuilder.append(year).append("-").append(month).append("-").append(day).toString();
    }
}