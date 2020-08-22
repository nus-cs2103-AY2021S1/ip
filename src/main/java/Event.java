package main.java;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate duration;

    Event(String title, LocalDate duration) {
        super(title);
        this.duration = duration;
    }

    Event(String title, boolean isDone, LocalDate duration) {
        super(title, isDone);
        this.duration = duration;
    }

    static Event of(String command) throws DukeException {
        if (command.length() <= 6) {
            throw new DukeException("Event cannot be empty.");
        }
        String[] split = command.substring(6).split("\\s+/at\\s+");
        if (split.length != 2) {
            throw new DukeException("Wrong format.");
        }
        try {
            return new Event(split[0], LocalDate.parse(split[1]));
        } catch (DateTimeException e) {
            System.out.println("Please provide date in yyyy-mm-dd format.");
            return null;
        }
    }

    @Override
    LocalDate getDate() {
        return this.duration;
    }

    public String toString() {
        String date = this.duration.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

    public String print() {
        return "E | " + super.print() + " | " + this.duration;
    }
}
