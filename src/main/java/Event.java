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

        var sc = new Scanner(input);

        sc.useDelimiter("/");
        var item = sc.next().strip();
        var when = sc.next().strip();

        if (item.isEmpty()) {

            throw new InvalidInputException("task description cannot be empty", getUsage());

        } else if (!when.startsWith("at ")) {

            throw new InvalidInputException("event requires a date", getUsage());
        }

        when = when.substring(3).strip();
        return new Event(item, when);
    }

    private static String getUsage() {

        return "event <description> /at <time>";
    }
}
