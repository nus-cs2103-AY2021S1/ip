package main.java.duke.task;

import main.java.duke.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String title, LocalDate deadline) {
        super(title);
        this.deadline = deadline;
    }

    public Deadline(String title, boolean isDone, LocalDate deadline) {
        super(title, isDone);
        this.deadline = deadline;
    }

    public static Deadline of(String command) throws DukeException {
        if (command.length() <= 9) {
            throw new DukeException("Deadline cannot be empty.");
        }
        String[] split = command.substring(9).split("\\s+/by\\s+");
        if (split.length != 2) {
            throw new DukeException("Wrong format.");
        }
        try {
            return new Deadline(split[0], LocalDate.parse(split[1]));
        } catch (DateTimeException e) {
            throw new DukeException("Please provide date in yyyy-mm-dd format.");
        }
    }

    @Override
    public LocalDate getDate() {
        return this.deadline;
    }

    public String toString() {
        String date = this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    public String print() {
        return "D | " + super.print() + " | " + this.deadline;
    }
}
