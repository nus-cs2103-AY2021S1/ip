// Event.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ikura.util.InvalidInputException;

/**
 * A class representing a Event task. It has a description (name), and a date.
 */
public class Event extends Task {

    private final LocalDate eventDate;

    /**
     * Constructs a new Event task with the given title and date.
     *
     * @param title    the Event's title.
     * @param deadline the date of this event.
     */
    public Event(String title, LocalDate date) {
        super(title);
        this.eventDate = date;
    }

    /**
     * Constructs a new Event task with the given title, description and date.
     *
     * @param title       the Event's description.
     * @param description the Event's description.
     * @param deadline    the date of this event.
     */
    public Event(String title, String description, LocalDate date) {
        super(title, description);
        this.eventDate = date;
    }

    /**
     * Gets the date of the event.
     *
     * @return the date.
     */
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
            && ((Event) other).getTitle().equals(this.getTitle())
            && ((Event) other).getEventDate().equals(this.getEventDate())
            && ((Event) other).getDescription().equals(this.getDescription());
    }

    /**
     * Parses an Event from the given input. The input should be of the
     * form "event (description) /at (date)", where (date) is in the form
     * "yyyy-mm-dd".
     *
     * @param  input the user's input.
     * @return the Event with the given description and date.
     * @throws InvalidInputException if the input was malformed.
     */
    public static Event parse(String input) throws InvalidInputException {

        var desc = TaskParser.parse("event", input, Optional.of("at"), getUsage());
        assert desc.hasTitle() && desc.hasDate();

        if (desc.hasDescription()) {
            return new Event(desc.getTitle().get(), desc.getDescription().get(), desc.getDate().get());
        } else {
            return new Event(desc.getTitle().get(), desc.getDate().get());
        }
    }

    /**
     * Gets the usage of the event command; this is the expected format of the input
     * passed to the parse() method.
     *
     * @return the usage.
     */
    private static String getUsage() {
        return "event <description> /at <date>";
    }
}
