package com.example.qiniu.qiniuyun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liuying
 * @version 1.0
 * @date 2020/6/10 19:17
 */

public class DateUtil {
    /**
     * 预设不同的时间格式
     */
    //精确到年月日（英文） eg:2019-12-31
    public static String FORMAT_LONOGRAM = "yyyy-MM-dd";
    //精确到时分秒的完整时间（英文） eg:2010-11-11 12:12:12
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
    //精确到毫秒完整时间（英文） eg:2019-11-11 12:12:12.55
    public static String FORMAT_LONOGRAM_MILL = "yyyy-MM-dd HH:mm:ss.SSS";
    //精确到年月日（中文）eg:2019年11月11日
    public static String FORMAT_LONOGRAM_CN = "yyyy年MM月dd日";
    //精确到时分秒的完整时间（中文）eg:2019年11月11日 12时12分12秒
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日 HH时MM分SS秒";
    //精确到毫秒完整时间（中文）
    public static String FORMAT_LONOGRAM_MILL_CN = "yyyy年MM月dd日HH时MM分SS秒SSS毫秒";
    /**
     * 预设默认的时间格式
     */
    public static String getDefaultFormat() {
        return FORMAT_FULL;
    }
    /**
     * 预设格式格式化日期
     */
    public static String format(Date date) {
        return format(date,getDefaultFormat());
    }
    /**
     * 自定义格式格式化日期
     */
    public static String format(Date date, String format) {
        String value = "";
        if(date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            value = sdf.format(date);
        }
        return value;
    }
    /**
     * 根据预设默认格式，返回当前日期
     */
    public static String getNow() {
        return format(new Date());
    }
    /**
     * 自定义时间格式，返回当前日期
     */
    public static String getNow(String format) {
        return format(new Date(),format);
    }
    /**
     *根据预设默认时间 String->Date
     */
    public static Date parse(Date date, String strDate) {
        return parse(strDate,getDefaultFormat());
    }
    /**
     * 自定义时间格式：Stirng->Date
     */
    public static Date parse(String strDate,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(strDate);
        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获取指定日期增加年
     * @param num  正数往后推，负数往前移
     * Calendar:它为特定瞬间与一组诸如 YEAR、MONTH、DAY_OF_MONTH、HOUR
     *                 等日历字段之间的转换提供了一些方法，并为操作日历字段（例如获得下星期的日期）提供了一些方法。
     */
    public static Date addYear(Date date,int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, num);
        return cal.getTime();
    }
    /**
     * 获取指定日期增加整月
     * @param date
     * @param num 整数往后推，负数往前移
     * @return
     */
    public static Date addMonth(Date date,int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, num);
        return cal.getTime();
    }
    /**
     * 获取指定日期增加天数
     * @param date
     * @param num 整数往后推，负数往前移
     * @return
     */
    public static Date addDay(Date date,int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, num);
        return cal.getTime();
    }
    /**
     * 获取指定日期增加分钟
     * @param date
     * @param num 整数往后推，负数往前移
     * @return
     */
    public static Date addMinute(Date date,int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, num);
        return cal.getTime();
    }
    /**
     * 获取时间戳 eg:yyyy-MM-dd HH:mm:ss.S
     * @return
     */
    public static String getTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_LONOGRAM_MILL);
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }
    /**
     * 获取日期的年份
     * @param date
     * @return
     */
    public static String getYear(Date date) {
        return format(date).substring(0,4);
    }
    /**
     * 获取年份+月
     */
    public static String getYearMonth(Date date) {
        return format(date).substring(0, 7);
    }
    /**
     *获取日期的小时数
     */
    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }
    /**
     *   自定义时间格式字符串距离今天的天数
     * @param strDate
     * @param format
     * @return
     */
    public static int countDays(String strDate,String format) {
        long time = Calendar.getInstance().getTime().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(strDate,format));
        long diff = cal.getTime().getTime();
        long a = time/1000;
        long b = diff/1000;
        return (int) (a - b)/3600/24;
    }
    /**
     * 预设格式的字符串距离今天的天数
     * @param strDate
     * @return
     */
    public static int countDays(String strDate) {
        return countDays(strDate,getDefaultFormat());
    }
    /**
     * 获取天数差值(依赖时间)
     * @param date1
     * @param date2
     * @return
     */
    public static int diffDays(Date date1,Date date2) {
        if(date1 == null || date2 == null) {
            return 0;
        }
        return (int) (Math.abs(date1.getTime() - date2.getTime()) / (60 * 60 * 24 * 1000));
    }
    /**
     * 获取年份差值
     * @param year1
     * @param year2
     * @return
     */
    public static int diffYear(Date year1,Date year2) {
        return diffDays(year1,year2) / 365;
    }
    /**
     * 获取天数差值(依赖Date类型的日期)
     * @param d1
     * @param d2
     * @return
     */
    public static int diffByDays(Date d1,Date d2) {
        Date s1 = parse(format(d1,FORMAT_LONOGRAM),FORMAT_LONOGRAM);
        Date s2 = parse(format(d2,FORMAT_LONOGRAM),FORMAT_LONOGRAM);
        return diffDays(s1,s2);
    }
    /**
     * 获取时间分割集合
     *
     * @param date 查询日期
     * @param strs 带拆分的时间点
     * @return
     */
    public static List<Date> collectTimes(Date date, String[] strs){
        List<Date> result = new ArrayList<Date>();
        List<String> times = Arrays.asList(strs);
        String dateStr = format(date,FORMAT_LONOGRAM);
        String pattern = FORMAT_LONOGRAM + "K";
        if(times.size() > 0 ) {
            times.stream().forEach(t -> {
                result.add(parse(date +" "+ t,pattern));
            });
        }
        return result;
    }

    /**
     * 根据日期查询当前为周几
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"7","1","2","3","4","5","6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK);   //1--7的值,对应：星期日，星期一，星期二，星期三....星期六
        //System.out.println(w);
        return weekDays[w-1];

    }

    /**
     * 将时间转换成汉字
     * @param hour
     * @return
     */
    public static String hourToCn(String hour) {
        String[] timeArray = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
        String[] hourArray = hour.split(":");
        int hourInt = Integer.parseInt(hourArray[0]);
        int minute = Integer.parseInt(hourArray[1]);
        String result = intToCn(hourInt,timeArray) + "点\n" + intToCn(minute,timeArray) + "分";
        return result;
    }
    private static String intToCn(int hourInt, String[] timeArray) {
        String result = "";
        if(hourInt >= 0 && hourInt <= 10) {
            result += timeArray[hourInt] + "\n";
        } else if (hourInt >= 11 && hourInt <= 19) {
            result += (timeArray[10] + "\n" + timeArray[hourInt % 10]) + "\n";
        }else {
            result += (timeArray[hourInt / 10] + "\n" + timeArray[10]) + "\n" + (hourInt % 10 == 0 ? "" : timeArray[hourInt % 10] + "\n");
        }
        return result;
    }
    /**
     * 获取当前日期后的一周时间，并返回LinkedHashMap<String, Date>
     * @param startTime
     * @return
     */
    public static LinkedHashMap<String, Date> dateAfterWeek(String startTime) {
        LinkedHashMap<String, Date> result = new LinkedHashMap<>();
        try {
            Date date = parse(startTime,FORMAT_LONOGRAM);
            for (int i = 0; i < 7; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(calendar.DATE, i);  //把日期往后增加一天,整数往后推,负数往前移动  时间戳转时间
                Date newDate = calendar.getTime();
                String str = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
                result.put(str, newDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 获取当前日期 后的一周时间，并返回yyyy-MM-dd字符串数组
     * @param startTime
     * @return
     */
    public static String[] dateAfterWeekArray(String startTime) {
        String weekArray[] = new String[7];
        try {
            Date date = parse(startTime,FORMAT_LONOGRAM);
            for (int i = 0; i < 7; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(calendar.DATE, i);//把日期往后增加一天,整数往后推,负数往前移动  时间戳转时间
                Date newDate = calendar.getTime();
                weekArray[i] = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weekArray;
    }
    /**
     * 根据传入的时间获取本周开始（0-表示本周，1-表示下周，-1-表示上周  ）
     * @param date
     * @return
     */
    public static String getMonDayToDate(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(date, "yyyy-MM-dd"));
        // N：0-表示本周，1-表示下周，-1-表示上周
        cal.add(Calendar.DATE, 0 * 7);
        // Calendar.MONDAY 表示获取周一的日期; Calendar.WEDNESDAY:表示周三的日期
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return format(cal.getTime());
    }

    /**
     * 获取指定日期，所对应的周，的开始日期和结束日期（按中国周）
     * @param todayTime :"2017-03-15"
     * @return arr[0] 第一天日期 ；arr[1]最后一天日期
     * @throws ParseException
     */
    public static String[] getWeekStartandEndDate(String todayTime) throws ParseException{
        String[] arr = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(todayTime));
        int d = 0;
        if(cal.get(Calendar.DAY_OF_WEEK)==1){
            d = -6;
        }else{
            d = 2-cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        //所在周开始日期
        arr[0]=sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        //所在周结束日期
        arr[1]=sdf.format(cal.getTime());
        return arr;
    }

    /**
     * 获取指定日期，所对应的月，的开始日期和结束日期（按中国周）
     * @param todayTime:"2017-03-15"
     * @return arr[0] 第一天日期 ；arr[1]最后一天日期
     * @throws ParseException
     */
    public static String[] getMonthStartAndEndDate(String todayTime) throws ParseException{
        String[] arr = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(todayTime));
        c.set(Calendar.DAY_OF_MONTH, 1);
        arr[0] =sdf.format(c.getTime());
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        arr[1]=sdf.format(c.getTime());
        return arr;

    }
    /**
     * 获取指定日期，所对应的年，的开始日期和结束日期（按中国周）
     * @param todayTime :"2017-03-15"
     * @return arr[0] 第一天日期 ；arr[1]最后一天日期
     * @throws ParseException
     */
    public static String[] getYearStartAndEndDate(String todayTime) throws ParseException{
        String[] arr = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(todayTime));
        c.set(Calendar.DAY_OF_YEAR, 1);
        arr[0] =sdf.format(c.getTime());
        c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
        arr[1]=sdf.format(c.getTime());
        return arr;
    }

    /**
     * 得到某一天的该星期的第一日 00:00:00
     *
     * @param date
     * @param firstDayOfWeek
     *            一个星期的第一天为星期几
     *
     * @return
     */
    public static Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.setFirstDayOfWeek(firstDayOfWeek);//设置一星期的第一天是哪一天
        cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);//指示一个星期中的某天
        cal.set(Calendar.HOUR_OF_DAY, 0);//指示一天中的小时。HOUR_OF_DAY 用于 24 小时制时钟。例如，在 10:04:15.250 PM 这一时刻，HOUR_OF_DAY 为 22。
        cal.set(Calendar.MINUTE, 0);//指示一小时中的分钟。例如，在 10:04:15.250 PM 这一时刻，MINUTE 为 4。
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    /**
     * 获取上周五时间
     */
    public Date lastFirday() {
       //作用防止周日得到本周日期
       Calendar calendar = Calendar.getInstance();
       while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
           calendar.add(Calendar.DAY_OF_WEEK, -1);
       }
       int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
       int offset = 7 - dayOfWeek;
       calendar.add(Calendar.DATE, offset - 9);
       return DateUtil.getFirstDayOfWeek(calendar.getTime(), 6);//这是从上周日开始数的到本周五为6

    }

    /**
     * 获取上周一时间
     */
    public Date lastMonday() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset = 1 - dayOfWeek;
        calendar.add(Calendar.DATE, offset - 7);
        return DateUtil.getFirstDayOfWeek(calendar.getTime(), 2);
    }


    //获取两个时间段之间  间隔几周
    public static int dateDiff(Date d1, Date d2) throws Exception {
        long n1 = d1.getTime();
        long n2 = d2.getTime();
        long diff = Math.abs(n1 - n2);
        diff /= 3600 * 1000 * 24;
        if(diff % 7 != 0)
            return (int)(diff / 7 + 1);
        return (int)(diff / 7);
    }

    /**
     * 获取两个日期相差的月数
     */
    public static int getMonthDiff(Date d1,Date d2){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval--;
        }
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) {
            monthInterval--;
        }
        monthInterval %= 12;
        int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);
        return monthsDiff;
    }

    /**
     * 两个日期之间的所有月份
     * @param minDate 2018-01
     * @param maxDate 2018-06
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        min = null;max = null;curr = null;
        return result;
    }

    /**
     * 根据出生日期，计算年龄
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static  int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            }else{
                age--;//当前月份在生日之前，年龄减一
            }
        } return age;
    }


    public static void main(String[] args) {
        //String time = format(new Date());
        //String weekOfDate = getWeekOfDate(new Date());
        //int countDays = countDays("2019-12-22",FORMAT_LONOGRAM);
        //Calendar cal = Calendar.getInstance();
//        long time = cal.getTime().getTime();
//        System.out.println("星期："+weekOfDate);
//        String hourToCn = hourToCn(format(new Date()).substring(11, 19));
//        System.out.print(hourToCn);
//        String[] dateAfterWeekArray = dateAfterWeekArray(format(new Date()));
//        for (int i = 0; i < dateAfterWeekArray.length; i++) {
//            System.out.println(dateAfterWeekArray[i]);
//        }
        String monDayToDate = getMonDayToDate(format(new Date()));
        System.out.println(monDayToDate);

    }


}
