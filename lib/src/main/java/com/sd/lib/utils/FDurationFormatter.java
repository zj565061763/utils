package com.sd.lib.utils;

import java.util.Calendar;
import java.util.Date;

public class FDurationFormatter
{
    public final long mTargetTime;
    private final Calendar mTargetCalendar = Calendar.getInstance();
    public final int mYear;
    public final int mMonth;
    public final int mWeekOfYear;
    public final int mWeekOfMonth;
    public final int mDayOfYear;
    public final int mDayOfMonth;
    public final int mDayOfWeek;
    public final int mHourOfDay;
    public final int mMinute;
    public final int mSecond;

    private final Calendar mCurrentCalendar = Calendar.getInstance();

    public FDurationFormatter(long targetTime)
    {
        mTargetTime = targetTime;
        mTargetCalendar.setTime(new Date(targetTime));

        mYear = mTargetCalendar.get(Calendar.YEAR);
        mMonth = mTargetCalendar.get(Calendar.MONTH);
        mWeekOfYear = mTargetCalendar.get(Calendar.WEEK_OF_YEAR);
        mWeekOfMonth = mTargetCalendar.get(Calendar.WEEK_OF_MONTH);
        mDayOfYear = mTargetCalendar.get(Calendar.DAY_OF_YEAR);
        mDayOfMonth = mTargetCalendar.get(Calendar.DAY_OF_MONTH);
        mDayOfWeek = mTargetCalendar.get(Calendar.DAY_OF_WEEK);
        mHourOfDay = mTargetCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mTargetCalendar.get(Calendar.MINUTE);
        mSecond = mTargetCalendar.get(Calendar.SECOND);
    }

    public String format()
    {
        final long currentTime = System.currentTimeMillis();
        final Calendar calendar = mCurrentCalendar;
        calendar.setTime(new Date(currentTime));

        final int year = calendar.get(Calendar.YEAR);
        if (year != mYear)
        {
            // 不在同一年
            return formatTimeAll();
        }

        final int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        final int dayBefore = dayOfYear - mDayOfYear;
        if (dayBefore < 0)
        {
            return formatFuture();
        } else if (dayBefore == 0)
        {
            return formatToday();
        } else if (dayBefore == 1)
        {
            return formatYesterday();
        } else if (dayBefore == 2)
        {
            return formatDayBeforeYesterday();
        } else
        {
            int week = calendar.get(Calendar.WEEK_OF_YEAR);
            int targetWeek = mWeekOfYear;

            final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY)
                week--;

            final int targetDayOfWeek = mDayOfWeek;
            if (targetDayOfWeek == Calendar.SUNDAY)
                targetWeek--;

            if (week == targetWeek)
            {
                // 同一周
                return formatDayInSameWeek();
            } else
            {
                return formatTimeAll();
            }
        }
    }

    protected String formatFuture()
    {
        return formatTimeAll();
    }

    protected String formatToday()
    {
        return formatCommonPart();
    }

    protected String formatYesterday()
    {
        return "昨天 " + formatCommonPart();
    }

    protected String formatDayBeforeYesterday()
    {
        return "前天 " + formatCommonPart();
    }

    protected String formatDayInSameWeek()
    {
        return formatDayOfWeek(mDayOfWeek) + " " + formatCommonPart();
    }

    protected String formatTimeAll()
    {
        return mYear + "-" + formatZero(formatMonthValue(mMonth)) + "-" + formatZero(mDayOfMonth) + " " + formatCommonPart();
    }

    protected String formatCommonPart()
    {
        return formatZero(mHourOfDay) + ":" + formatZero(mMinute);
    }

    protected String formatDayOfWeek(int dayOfWeek)
    {
        switch (dayOfWeek)
        {
            case Calendar.SUNDAY:
                return "星期日";
            case Calendar.MONDAY:
                return "星期一";
            case Calendar.TUESDAY:
                return "星期二";
            case Calendar.WEDNESDAY:
                return "星期三";
            case Calendar.THURSDAY:
                return "星期四";
            case Calendar.FRIDAY:
                return "星期五";
            case Calendar.SATURDAY:
                return "星期六";
            default:
                throw new IllegalArgumentException("unknown day of week:" + dayOfWeek);
        }
    }

    protected int formatMonthValue(int month)
    {
        int value = month + 1;
        if (value > 12)
            value = 12;
        return value;
    }

    protected String formatZero(int value)
    {
        if (value < 10)
        {
            return "0" + value;
        } else
        {
            return String.valueOf(value);
        }
    }
}
