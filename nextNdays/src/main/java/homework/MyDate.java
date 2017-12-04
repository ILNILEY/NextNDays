package homework;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDate {
    int year;
    int month;
    int day;
    //空构造函数
    public MyDate() {

    }
    //以Java类型日期为参数的构造函数
    public MyDate(Date date) {

        if (date != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            this.year = calendar.get(Calendar.YEAR);
            this.month = calendar.get(Calendar.MONTH) + 1;
            this.day = calendar.get(Calendar.DAY_OF_MONTH);
        }
    }
    //以年月日为参数的构造函数
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int compareTo(MyDate otherDate) {
        if (otherDate == null) {
            return -2;//日期为空返回-2
        }
        if (year > otherDate.getYear()) {
            return 1;//年份较大返回1
        }
        else if (year == otherDate.getYear()) {
            if (month > otherDate.getMonth()) {
                return 1;//月份较大返回1
            }
            else if (month == otherDate.getMonth()) {
                if (day > otherDate.getDay()) {
                    return 1;//日期较大返回1
                }
                else if (day == otherDate.getDay()) {
                    return 0;
                }
                return -1;
            }

            return -1;
        }
        return -1;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "年" + month + "月" + day + "日";
    }
}