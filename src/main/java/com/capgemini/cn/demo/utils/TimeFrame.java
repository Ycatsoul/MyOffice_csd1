package com.capgemini.cn.demo.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 时间处理工具类，主要进行时间的计算
 * @Author: chunlei.wang
 * @Date: 2018/08/29
 */
public class TimeFrame {

    /**
     * 一天的开始
     */
    public static Date startOfDay(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);

        //确定时分秒毫秒
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        return now.getTime();
    }

    /**
     * 一天的结束(第二天的开始)
     */
    public static Date endOfDay(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);

        now.add(Calendar.DAY_OF_MONTH, 1);

        //确定时分秒毫秒
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        return now.getTime();
    }

    /**
     * 计算本周的第一天
     * @param date
     * @return
     */
    public static Date firstDateOfWeek(Date date) {
        // 获取日历的实例
        Calendar now = Calendar.getInstance();
        // 用传递的参数设置日历
        now.setTime(date);

        /**
         * 计算为当前周的第一天，确定年月日
         * int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
         * int subtractday = -dayOfWeek + 1;
         * now.add(Calendar.DAY_OF_WEEK, subtractday);
         * 直接在本周循环内设置为第一天
         */
        now.set(Calendar.DAY_OF_WEEK, 1);

        //确定时分秒毫秒
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        return now.getTime();
    }

    /**
     * 计算本周的最后一天
     */
    public static Date lastDateOfWeek(Date date) {
        // 获取日历的实例
        Calendar now = Calendar.getInstance();
        // 用传递的参数设置日历
        now.setTime(date);

        /**
         * 计算为当前周的最后一天
         * int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
         * int addday = 7 - dayOfWeek + 1;
         * now.add(Calendar.DAY_OF_WEEK, addday);
         * 开始使用计算，后来发现了一个好的方法，Calendar 里提供了在指定周期内进行循环回滚
         * 下面就是设置为本周的第一天，在退回一天就是本周的最后一天了
         */
        now.set(Calendar.DAY_OF_WEEK, 1);
        now.roll(Calendar.DAY_OF_WEEK, -1);
        // 但是如果是做范围的话，应该是下周一的第一天的凌晨为最后期限
        now.add(Calendar.DAY_OF_WEEK, 1);

        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        return now.getTime();
    }

    /**
     * 计算本月的第一天
     */
    public static Date firstDateOfWonth(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DAY_OF_MONTH, 1);

        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        return now.getTime();
    }

    /**
     * 计算本月的最后一天
     */
    public static Date lastDateOfMonth(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);

        // 首先设置为第一天，再回滚一天，是在当前月回滚，那么一个循环的最后就是最后一天了
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.roll(Calendar.DAY_OF_MONTH, -1);

        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        return now.getTime();
    }
}
