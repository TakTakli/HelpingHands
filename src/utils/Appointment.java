package utils;

public class Appointment {
    private int day;
    private int month;
    private int year;
    private String type;
    private String time;

    public Appointment(int day, int month, int year, String type, String time) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.type = type;
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }
}