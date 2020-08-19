// Event.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;

public class Event extends Task {

	private final String eventDate;

    public Event(String name, String date) {

        super(name);
		this.eventDate = date;
    }

    @Override
    public String toString() {

        return String.format("[E] %s (at: %s)", super.toString(), this.eventDate);
    }

    public static Event parse(String input) throws InvalidInputException {

        var slash = input.indexOf('/');

        if (slash == 0 || input.isEmpty()) {
            throw new InvalidInputException("task description cannot be empty", getUsage());

        } else if (slash == -1) {
            throw new InvalidInputException("event requires a date", getUsage());
        }

        var item = input.substring(0, slash).strip();
        var when = input.substring(slash + 1).strip();

        assert !item.isEmpty();

        if (!when.startsWith("at ")) {
            throw new InvalidInputException("incorrect date specification", getUsage());
        }

        when = when.substring(3).strip();
        return new Event(item, when);
    }

    private static String getUsage() {

        return "event <description> /at <time>";
    }
}
