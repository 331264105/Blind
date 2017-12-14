package com.didu.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/9.
 */
public class CreateUid {
    public static String CreateUid(int id){
        int y , m , d , length;
        String pre;
        Calendar cal= Calendar.getInstance();
        y=cal.get(Calendar.YEAR);
        m=cal.get(Calendar.MONTH)+1;
        d=cal.get(Calendar.DATE);
        String sy = String.valueOf(y);
        String sm = String.valueOf(m);
        String sd = String.valueOf(d);
        String sid = String.valueOf(id);
        String suby = sy.substring(2,4);
        pre=suby+sm+sd;
        length=5-sid.length();
        for (int i=0;i<length;i++){
            pre+="0";
        }
        String uid = pre + sid;
        return  uid;
    }
}
