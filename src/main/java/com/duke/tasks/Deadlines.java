package com.duke.tasks;

import com.duke.parser.Parser;

import java.time.LocalDate;

public class Deadlines extends Task {
    protected LocalDate date;
    protected int time;

    public Deadlines(String task, String dateAndTime) {
        //date = 2019-12-02 1800
        String[] dateAndTimeArr = dateAndTime.split(" ");
        this.task = task;
        this.date = LocalDate.parse(dateAndTimeArr[0]);
        this.time = Integer.parseInt(dateAndTimeArr[1]);
    }

    public Deadlines(String task, String dateAndTime, boolean isDone) {
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
        String doneIndicator = this.isDone ? "[✓]" : "[✗]";
        int dayOfMonth = this.date.getDayOfMonth();
        String month = this.date.getMonth().toString();
        int year = this.date.getYear();
        String date = dayOfMonth + " " + month + " " + year;
        String time = "";
        if (this.time < 100) {
            time = "12" + this.time + "am";
        } else if (this.time < 1200) {
            time = this.time + "am";
        } else {
            time = (this.time - 1200) + "pm";
        }

        return "[D]" + doneIndicator + " " + this.task + " (by: " + date + ", " + time + ")";
    }

    /**
     * Returns a string representation of the Deadline object to be saved in persistent file.
     *
     * @return String Returns a string representation of the Deadline object to be saved in persistent file.
     */
    @Override
    public String parseToSaveFormat() {
        String res = "";
        String isDoneStr = this.isDone ? "1" : "0";
        String dateSaveFormatStr = Parser.parseDateToSaveFormat(this.date);
        res = "D - " + isDoneStr + " - " + this.task + " - " + dateSaveFormatStr + " " + this.time;
        return res;
    }

}
