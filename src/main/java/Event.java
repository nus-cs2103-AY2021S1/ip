package main.java;

import java.time.format.TextStyle;
import java.util.Locale;

class Event extends Task {

    Event(String description, String dateTimeString) {
        super(false, description, dateTimeString);
    }

    Event(boolean isCompleted, String description, String dateTimeString) {
        super(isCompleted, description, dateTimeString);
    }

    @Override
    String[] getDataString() {
        return new String[] {"deadline", String.valueOf(isCompleted), description, String.valueOf(dateTime.getYear()),
                dateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH), String.valueOf(dateTime.getDayOfMonth()),
                String.valueOf(dateTime.getHour()), String.valueOf(dateTime.getMinute())};
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + super.getDateTimeString() + ")";
    }
}
