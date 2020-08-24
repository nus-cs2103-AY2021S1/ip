// Deadline.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ikura.util.InvalidInputException;

public class Deadline extends Task {

    private final LocalDate deadline;

    public Deadline(String name, LocalDate deadline) {

        super(name);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {

        return String.format("[D] %s (by: %s)", super.toString(),
            this.deadline.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
    }

    public static Deadline parse(String input) throws InvalidInputException {

        var parts = DatedTask.parse("deadline", input, "by", getUsage());
        assert parts.size() == 2;

        return new Deadline(parts.get(0), DatedTask.parseDate(parts.get(1)));
    }

    private static String getUsage() {

        return "deadline <description> /by <date>";
    }
}
