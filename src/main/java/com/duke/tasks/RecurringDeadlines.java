package com.duke.tasks;

import java.time.LocalDate;

import com.duke.parser.Parser;

public class RecurringDeadlines extends RecurringTask {
    public static final String DEADLINE_SYMBOL = "[D]";
    protected LocalDate date;
    protected int time;
    /**
     * Constructor for RecurringDeadlines.
     * dateAndTime is of YYYY-MM-DD 10pm format.
     *
     * @param task Task description.
     * @param dateAndTime date and time of deadline.
     * @param recurringPeriod the period of recurrence in days.
     */
    public RecurringDeadlines(String task, String dateAndTime, String recurringPeriod) {
        String[] dateAndTimeArr = dateAndTime.split(" ");
        this.task = task;
        this.date = LocalDate.parse(dateAndTimeArr[0]);
        this.time = Integer.parseInt(dateAndTimeArr[1]);
        this.recurringPeriod = recurringPeriod;
    }

    /**
     * Constructor for RecurringDeadlines.
     * dateAndTime is of YYYY-MM-DD 10pm format.
     *
     * @param task Task description.
     * @param dateAndTime date and time of deadline.
     * @param isDone Whether task is done or not.
     * @param recurringPeriod the period of recurrence in days.
     */
    public RecurringDeadlines(String task, String dateAndTime, boolean isDone, String recurringPeriod) {
        String[] dateAndTimeArr = dateAndTime.split(" ");

        this.task = task;
        this.date = LocalDate.parse(dateAndTimeArr[0]);
        this.time = Integer.parseInt(dateAndTimeArr[1]);
        this.isDone = isDone;
        this.recurringPeriod = recurringPeriod;
    }

    /**
     * Returns a string representation of the RecurringDeadline object to be saved in persistent file.
     *
     * @return String Returns a string representation of the RecurringDeadline object to be saved in persistent file.
     */
    @Override
    public String parseToSaveFormat() {
        String isDoneStr = this.isDone ? "1" : "0";
        String dateSaveFormatStr = Parser.parseDateToSaveFormat(this.date);
        String res = "D - " + isDoneStr + " - " + this.task + " - " + dateSaveFormatStr + " " + this.time;
        return res;
    }

    /**
     * Returns a string representation of the contents of the specified array.
     *
     * @return String Returns a string representation of the contents of the specified array.
     */
    @Override
    public String toString() {
        String doneIndicator = getDoneIndicator();
        String date = generateDateString();
        String time = generateTimeString();
        assert !time.equals("");
        return RECURRING_SYMBOL + " " + DEADLINE_SYMBOL
                + doneIndicator + " " + this.task + " (by: " + date + ", " + time + ")";
    }

    private String generateDateString() {
        int dayOfMonth = this.date.getDayOfMonth();
        String month = this.date.getMonth().toString();
        int year = this.date.getYear();
        return dayOfMonth + " " + month + " " + year;
    }

    private String generateTimeString() {
        String time;
        if (this.time < 100) {
            time = "12" + this.time + "am";
        } else if (this.time < 1200) {
            time = this.time + "am";
        } else {
            time = (this.time - 1200) + "pm";
        }
        return time;
    }
}
