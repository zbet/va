package io.virtualapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import io.virtualapp.R;

import static android.content.Context.MODE_PRIVATE;

public class StringUtils {
    //腾讯地图只有6位
    private static boolean isTencentMap=true;
    public static boolean isString(String tag) {
        return tag != null && !"".equals(tag);
    }

    public static double doubleFor6(double num) {
        if(isTencentMap)return num;
        return Double.parseDouble(doubleFor6String(num));
    }

    public static double doubleFor8(double num) {
        if(isTencentMap)return num;
        return Double.parseDouble(doubleFor8String(num));
    }

    public static String doubleFor8String(double num) {
        if(isTencentMap)return String.valueOf(num);
        DecimalFormat formater = new DecimalFormat();
        //保留几位小数
        formater.setMaximumFractionDigits(8);
        return formater.format(num);
    }

    public static String doubleFor6String(double num) {
        if(isTencentMap)return String.valueOf(num);
        DecimalFormat formater = new DecimalFormat();
        //保留几位小数
        formater.setMaximumFractionDigits(6);
        return formater.format(num);
    }

    public static String getTimeNoHour(long time) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
        calendar.setTimeInMillis(time);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);

        return String.format("%02d:%02d", h * 60 + m, s);
    }

    public static String getTime(long time) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
        calendar.setTimeInMillis(time);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);

        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public static String timeForString(Context context, long tag) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(tag);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String[] strs = context.getResources().getStringArray(R.array.weeks);
        String str = strs[week - 1];
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd [" + str + "] hh:mm"); //格式化当前系统日期
        return dateFm.format(tag);
    }

    //判断apk第一次导入的状态
    private static final String APP_COPY_STATUE = "APP_COPY_STATUE";

    public static void setAppCopystatue(Context c,Boolean isCopy,String typename ) {
        SharedPreferences preferences = c.getSharedPreferences(typename, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(APP_COPY_STATUE, isCopy);
        editor.apply();
    }

    public static boolean getAppCopystatue(Context c,String typename) {
        SharedPreferences preferences = c.getSharedPreferences(typename, MODE_PRIVATE);
        return preferences.getBoolean(APP_COPY_STATUE, false);
    }
}
