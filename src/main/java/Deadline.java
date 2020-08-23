package main.java;

import java.time.format.TextStyle;
import java.util.Locale;

class Deadline extends Task {

    Deadline(String description, String dateTimeString) {
        super(false, description, dateTimeString);
    }

    Deadline(boolean isCompleted, String description, String datetime) {
        super(isCompleted, description, datetime);
    }

    @Override
    String[] getDataString() {
        return new String[] {"deadline", String.valueOf(isCompleted), description, String.valueOf(dateTime.getYear()),
        dateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH), String.valueOf(dateTime.getDayOfMonth()),
                String.valueOf(dateTime.getHour()), String.valueOf(dateTime.getMinute())};
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + super.getDateTimeString() + ")";
    }
}
