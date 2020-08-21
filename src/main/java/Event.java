// Event.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;

public class Event extends Task {

	private final String eventDate;

    public Event(String name, String date) {

        super(name);
		this.eventDate = date;
    }

    public String getEventDate() {
        return this.eventDate;
    }

    @Override
    public String toString() {

        return String.format("[E] %s (at: %s)", super.toString(), this.eventDate);
    }

    public static Event parse(String input) throws InvalidInputException {

        var parts = DatedTask.parse("event", input, "at", getUsage());
        assert parts.size() == 2;

        return new Event(parts.get(0), parts.get(1));
    }

    private static String getUsage() {

        return "event <description> /at <time>";
    }
}
