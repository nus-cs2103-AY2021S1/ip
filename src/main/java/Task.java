package main.java;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

abstract class Task {

    final String description;
    final LocalDateTime dateTime;
    boolean isCompleted;

    Task(boolean isCompleted, String description, String dateTimeString) {
        this.isCompleted = isCompleted;
        this.description = description;

        if (dateTimeString == null) {
            this.dateTime = null;
        } else {
            // Assume dateTime format is yyyy-mm-dd-hh-mm
            String[] dateTimeParserString = dateTimeString.split("-");
            int[] dateTimeParserIntegers = new int[5];
            for (int i = 0; i < dateTimeParserIntegers.length; i++) {
                dateTimeParserIntegers[i] = Integer.parseInt(dateTimeParserString[i]);
            }
            this.dateTime = LocalDateTime.of(dateTimeParserIntegers[0], dateTimeParserIntegers[1],
                    dateTimeParserIntegers[2], dateTimeParserIntegers[3], dateTimeParserIntegers[4]);
        }
    }

    String getStatusIcon() {
        if (this.isCompleted) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    void completeTask() {
        this.isCompleted = true;
    }

    String getDateTimeString() {
        if (this.dateTime == null) {
            return null;
        } else {
            return this.dateTime.getYear() + " " +
                    this.dateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                    this.dateTime.getDayOfMonth() + " " +
                    this.dateTime.getHour() + ":" +
                    this.dateTime.getMinute();
        }
    }

    String[] getDataString() {
        return new String[] {"task", String.valueOf(isCompleted), description};
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
