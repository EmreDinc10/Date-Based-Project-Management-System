import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Date
 */
public class Date {

    LocalDate dateNow = LocalDate.now();

    private int day, month, year;
    private static ArrayList<Integer> numberOfDaysInAMonth = new ArrayList<Integer>();

    public int getYear() {
        return this.year;
    }
    public int getMonth() {
        return this.month;
    }
    public int getDay() {
        return this.day;
    }

    public void setYear(int year) {
        if (year <= dateNow.getYear()) {
            this.year = year;
        }
        else {
            this.year = dateNow.getYear();
        }

    }
    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        }
        else this.month = dateNow.getMonthValue();
    }

    private int initDaysInMonth() {
        if ((this.month <= 7 && this.month % 2 == 1) || (this.month > 7 && this.month % 2 == 0)) {
            for (int i = 0; i <= 31; i++) {
                    numberOfDaysInAMonth.add(i);
            }
        }
        else if (this.month == 2) {
            if (isLeapYear(this.year)) {
                for (int i = 0; i <= 29; i++) {
                    numberOfDaysInAMonth.add(i);
                }
            }
            else {
                for (int i = 0; i <= 28; i++) {
                    numberOfDaysInAMonth.add(i);
                }
            }
        }
        else {
            for (int i = 0; i <= 30; i++) {
                numberOfDaysInAMonth.add(i);
            }
        }
        int daysInAMonth = numberOfDaysInAMonth.size();
        return daysInAMonth;
    }
    private int initDaysInMonth(int month) {
        int daysInAMonth;
        if ((month <= 7 && this.month % 2 == 1) || (this.month > 7 && this.month % 2 == 0)) {
            daysInAMonth = 31;
        }
        else if (this.month == 2) {
            if (isLeapYear(this.year)) {
                daysInAMonth = 29;
            }
            else {
                daysInAMonth = 28;
            }
        }
        else {
            daysInAMonth = 30;
        }
        return daysInAMonth;
    } 

    public boolean isLeapYear(int year) {
        boolean isLeapYear = false;
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                isLeapYear = true;
            }
        }
        else if (year % 4 == 0) {
            isLeapYear = true;
        }
        return isLeapYear;
    }

    public void setDay(int day) {
        if (day > 0 && day <= initDaysInMonth()) {
            this.day = day;
        }
    }

    /** Constructor
     * 
     * @param year
     * @param month
     * @param day
     */
    public Date(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }
    
    /** Constructor
     * 
     * @param date in the YYYY-MM-DD format
     */
    public Date (String date) {
        int lastDashIndex = date.lastIndexOf('-');
        this.year = Integer.parseInt(date.substring(0, 4));
        this.month = Integer.parseInt(date.substring(5,lastDashIndex));
        this.day = Integer.parseInt(date.substring(lastDashIndex + 1));
    }

    /** Copy Constructor
     * 
     * @param date
     */
    public Date (Date date) {
        this.year = date.year;
        this.month = date.month;
        this.day = date.day;
    }

    public String toString() {
        String date = year + "-" + month + "-" + day;
        return date;
    }

    /**
     * 
     * @param date
     * @return
     */
    public int daysBetween(Date date) {
        // ArrayList<Integer> yearsBetween = new ArrayList<Integer>();
        int daysBetween = 0;
        if (this.year < date.year) {
            // the years between
            for (int i = 1; i < (date.year - this.year); i++) {
                if (isLeapYear(this.year + i)) {
                    daysBetween = daysBetween + 366;
                }
                else {
                    daysBetween = daysBetween + 365;
                }
            }
            // adding the lower bound year's days
            for (int i = 12; i > this.month; i--) {
                daysBetween = daysBetween + initDaysInMonth(i);
            }   
            daysBetween = daysBetween + (this.initDaysInMonth(this.getMonth()) - this.day);
            
            // adding the upper bound year's days
            for (int i = 1; i < date.month; i++) {
                daysBetween = daysBetween + initDaysInMonth(i);
            }
            daysBetween = daysBetween + date.day;
        }

        else if (date.year < this.year) {
            // the years between
            for (int i = 1; i < (this.year - date.year); i++) {
                if (isLeapYear(date.year + i)) {
                    daysBetween = daysBetween + 366;
                }
                else {
                    daysBetween = daysBetween + 365;
                }
            }
            // adding the lower bound year's days
            for (int i = 12; i > date.month; i--) {
                daysBetween = daysBetween + initDaysInMonth(i);
            }   
            daysBetween = daysBetween + (date.initDaysInMonth(date.getMonth()) - date.day);
            
            // adding the upper bound year's days
            for (int i = 1; i < this.month; i++) {
                daysBetween = daysBetween + initDaysInMonth(i);
            }
            daysBetween = daysBetween + this.day;
        }
         // if they are in the same year
        else {
            if (this.month - date.month != 0) {
                if (this.month < date.month) {
                    for (int i = this.month + 1; i < date.month; i++) {
                        daysBetween += initDaysInMonth(i);
                    }
                    daysBetween += (this.initDaysInMonth(this.getMonth()) - this.day);
                    daysBetween += date.day;
                }
                else if (this.month > date.month) {
                    for (int i = date.month + 1; i < this.month; i++) {
                        daysBetween += initDaysInMonth(i);
                    }
                    daysBetween += (date.initDaysInMonth(date.getMonth()) - date.day);
                    daysBetween += this.day;
                }
            }
            else {
                if (this.day < date.day) {
                    daysBetween += date.day - this.day;
                }
                else if (this.day > date.day) {
                    daysBetween += this.day - date.day;
                }
                else {
                    daysBetween = 0;
                }
            }
        }
        return daysBetween;
    }

    /**
     * 
     * @param date
     * @return
     */
    public boolean isBefore(Date date) {
        boolean isBefore = true;
        if (this.year > date.year) {
            isBefore = false;
        }
        else if (this.year == date.year) {
            if (this.month > date.month) {
                isBefore = false;
            }
            else if (this.month == date.month) {
                if (this.day > date.day) {
                    isBefore = false;
                }
            }
        }
        return isBefore;
    }

    /** this method won't return anything, 
     * it will just update the date object it has been invoked on
     * 
     * @param days
     */
    public Date addDays(int days) {
        while (days > 0) {
            if (this.day < this.initDaysInMonth(this.month)) {
                this.day++;
                days--;
            }
            else if (this.day == this.initDaysInMonth(this.month)) {
                if (this.month == 12) {
                    this.setMonth(1);
                    this.year++;
                }
                else {
                    this.setMonth(this.month + 1);
                }
                this.setDay(1);
                days--;
            }
        }
        // returns date object
        return this;
    }

}