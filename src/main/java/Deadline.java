// Deadline.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;

public class Deadline extends Task {

    private final String deadline;

    public Deadline(String name, String deadline) {

        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {

        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }

    public static Deadline parse(String input) throws InvalidInputException {

        var parts = DatedTask.parse("deadline", input, "by", getUsage());
        assert parts.size() == 2;

        return new Deadline(parts.get(0), parts.get(1));
    }

    private static String getUsage() {

        return "deadline <description> /by <time>";
    }
}
