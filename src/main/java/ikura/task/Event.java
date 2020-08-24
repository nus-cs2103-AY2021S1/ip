// Event.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ikura.util.InvalidInputException;

public class Event extends Task {

	private final LocalDate eventDate;

    public Event(String name, LocalDate date) {

        super(name);
		this.eventDate = date;
    }

    public LocalDate getEventDate() {
        return this.eventDate;
    }

    @Override
    public String toString() {

        return String.format("[E] %s (at: %s)", super.toString(),
            this.eventDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Event)
            && ((Event) other).getName().equals(this.getName())
            && ((Event) other).getEventDate().equals(this.getEventDate());
    }

    public static Event parse(String input) throws InvalidInputException {

        var parts = DatedTask.parse("event", input, "at", getUsage());
        assert parts.size() == 2;

        return new Event(parts.get(0), DatedTask.parseDate(parts.get(1)));
    }

    private static String getUsage() {

        return "event <description> /at <date>";
    }
}
