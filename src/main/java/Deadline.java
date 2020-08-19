// Deadline.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;

public class Deadline extends Task {

    private final String deadline;

    public Deadline(String name, String deadline) {

        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {

        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }

    public static Deadline parse(String input) throws InvalidInputException {

        var slash = input.indexOf('/');

        if (slash == 0 || input.isEmpty()) {
            throw new InvalidInputException("task description cannot be empty", getUsage());

        } else if (slash == -1) {
            throw new InvalidInputException("deadline requires a date", getUsage());
        }

        var item = input.substring(0, slash).strip();
        var when = input.substring(slash + 1).strip();

        assert !item.isEmpty();

        if (!when.startsWith("by ")) {
            throw new InvalidInputException("incorrect date specification", getUsage());
        }

        when = when.substring(3).strip();
        return new Deadline(item, when);
    }

    private static String getUsage() {

        return "deadline <description> /by <time>";
    }
}
