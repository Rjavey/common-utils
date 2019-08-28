package utils;


import config.DateTimeFormat;
import config.Time;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @Author: zr
 * @Description: 日期格式工具类
 * @Date: 11:40 2019/8/27
 * @Modified by:
 */
public class DataTimeUtil {

    /**
     * string 转date
     * @param dataTimeStr dateString
     * @return
     */
    public static Date strToDate(String dataTimeStr){
        if (StringUtils.isBlank(dataTimeStr)) {
            return null;
        }
        LocalDateTime ldt = LocalDateTime.parse(dataTimeStr);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 指定格式string转date
     * @param dataTimeStr
     * @param formatStr
     * @return
     */
    public static Date strToDate(String dataTimeStr, String formatStr){
        if (StringUtils.isBlank(dataTimeStr)) {
            return null;
        }
        LocalDateTime ldt = LocalDateTime.parse(dataTimeStr);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * date转string
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateTimeFormat.DEFAULT.getDataformat());
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     *
     * @param date
     * @param dateFormatter
     * @return
     */
    public static String dateToStr(Date date,String dateFormatter) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.isBlank(dateFormatter)){
            dateFormatter = DateTimeFormat.DEFAULT.getDataformat();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatter);
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 转为指定格式日期格式
     * @param dataTimeStr
     * @param dateFormatter
     * @return
     */
    public static String strTostr(String dataTimeStr,String dateFormatter) {
        if (StringUtils.isBlank(dataTimeStr)) {
            return null;
        }
        if (StringUtils.isBlank(dateFormatter)){
            dateFormatter = DateTimeFormat.DEFAULT.getDataformat();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatter);
        return LocalDateTime.parse(dataTimeStr).format(dateTimeFormatter);
    }

    /**
     * 增减时间
     * @param date
     * @param timeType
     * @param time
     * @return
     */
    public static Date plusTime(Date date, Time timeType,long time) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        switch (timeType){
            case YEAR:
                localDateTime.plusYears(time);
                break;
            case MONTH:
                localDateTime.plusMonths(time);
                break;
            case DAY:
                localDateTime.plusDays(time);
                break;
            case HOUR:
                localDateTime.plusHours(time);
                break;
            case MINUTE:
                localDateTime.plusMinutes(time);
            case SECOND:
                localDateTime.plusSeconds(time);
                break;
            default:
                break;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 计算时间差
     * @param startTime
     * @param endTime
     * @param chronoUnit 计算类型 日或小时
     * @return
     */
    public static long dateDiffDate(String startTime,String endTime,ChronoUnit chronoUnit){
        switch (chronoUnit){
            case DAYS:
                return ChronoUnit.DAYS.between(LocalDateTime.parse(startTime),LocalDateTime.parse(endTime));
            case HOURS:
                return ChronoUnit.HOURS.between(LocalDateTime.parse(startTime),LocalDateTime.parse(endTime));
            default:
                return 0;
        }

    }





}
