package main.java;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    Deadline(String title, LocalDate deadline) {
        super(title);
        this.deadline = deadline;
    }

    static Deadline of(String command) throws DukeException {
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
            System.out.println("Please provide date in yyyy-mm-dd format.");
            return null;
        }
    }

    @Override
    LocalDate getDate() {
        return this.deadline;
    }

    public String toString() {
        String date = this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
