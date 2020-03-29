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
        mDayOfMonth = mTargetCalendar.get(Calendar.DAY_OF_MONTH);
        mDayOfWeek = mTargetCalendar.get(Calendar.DAY_OF_WEEK);
        mHourOfDay = mTargetCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mTargetCalendar.get(Calendar.MINUTE);
        mSecond = mTargetCalendar.get(Calendar.SECOND);
    }

    public String format()
    {
        final long currentTime = System.currentTimeMillis();
        if (mTargetTime >= currentTime)
            return formatTimeAll();

        final Calendar calendar = mCurrentCalendar;
        calendar.setTime(new Date(currentTime));

        final int year = calendar.get(Calendar.YEAR);
        if (year != mYear)
        {
            // 不在同一年
            return formatTimeAll();
        }

        final int month = calendar.get(Calendar.MONTH);
        if (month != mMonth)
        {
            // 不在同一月
            return formatTimeAll();
        }

        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        final int dayBefore = dayOfMonth - mDayOfMonth;
        if (dayBefore == 0)
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
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
            int targetWeekOfMonth = mWeekOfMonth;

            final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY)
                weekOfMonth--;

            if (mDayOfWeek == Calendar.SUNDAY)
                targetWeekOfMonth--;

            if (weekOfMonth == targetWeekOfMonth)
            {
                // 同一周
                return formatDayInSameWeek();
            } else
            {
                return formatTimeAll();
            }
        }
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
