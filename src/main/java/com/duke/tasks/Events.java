package com.duke.tasks;

import java.time.LocalDate;

import com.duke.parser.Parser;

/**
 * Represents a Event task item.
 */

public class Events extends Task {
    public static final String EVENT_SYMBOL = "[E]";
    protected LocalDate date;
    protected int time;

    /**
     * Constructor for Events.
     * dateAndTime is of YYYY-MM-DD 10pm format.
     *
     * @param task Task description.
     * @param dateAndTime date and time of deadline.
     */
    public Events(String task, String dateAndTime) {
        // date = '2018-10-23 6pm'
        String[] dateAndTimeArr = dateAndTime.split(" ");
        this.task = task;
        this.date = LocalDate.parse(dateAndTimeArr[0]);
        this.time = Integer.parseInt(dateAndTimeArr[1]);
    }

    /**
     * Constructor for Events.
     * dateAndTime is of YYYY-MM-DD 10pm format.
     *
     * @param task Task description.
     * @param dateAndTime date and time of deadline.
     * @param isDone Whether task is done or not.
     */
    public Events(String task, String dateAndTime, boolean isDone) {
        String[] dateAndTimeArr = dateAndTime.split(" ");

        this.task = task;
        this.date = LocalDate.parse(dateAndTimeArr[0]);
        this.time = Integer.parseInt(dateAndTimeArr[1]);
        this.isDone = isDone;
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

        return EVENT_SYMBOL + doneIndicator + " " + this.task + " (at: " + date + ", " + time + ")";
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

    /**
     * Returns a string representation of the Deadline object to be saved in persistent file.
     *
     * @return String Returns a string representation of the Events object to be saved in persistent file.
     */
    @Override
    public String parseToSaveFormat() {
        String res = "";
        String isDoneStr = this.isDone ? "1" : "0";
        String dateSaveFormatStr = Parser.parseDateToSaveFormat(this.date);
        res = "E - " + isDoneStr + " - " + this.task + " - " + dateSaveFormatStr + " " + this.time;
        return res;
    }

}
