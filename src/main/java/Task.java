package main.java;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

abstract class Task {

    final String description;
    final LocalDate date;
    boolean isComplete;

    Task(String description, boolean isCompleted, LocalDate date) {
        this.date = date;
        this.description = description;
        this.isComplete = isCompleted;
    }

    String getStatusIcon() {
        if (this.isComplete) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    void completeTask() {
        this.isComplete = true;
    }

    String getDateString() {
        if (this.date == null) {
            return null;
        } else {
            return this.date.getYear() + " " +
                    this.date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                    this.date.getDayOfMonth();
        }
    }

    String[] getDataString() {
        return new String[] {"task", String.valueOf(isComplete), description};
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
