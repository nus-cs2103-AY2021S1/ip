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

        var sc = new Scanner(input);

        sc.useDelimiter("/");
        var item = sc.next().strip();
        var when = sc.next().strip();

        if (item.isEmpty()) {

            throw new InvalidInputException("task description cannot be empty", getUsage());

        } else if (!when.startsWith("by ")) {

            throw new InvalidInputException("deadline requires a date", getUsage());
        }

        when = when.substring(3).strip();
        return new Deadline(item, when);
    }

    private static String getUsage() {

        return "deadline <description> /by <time>";
    }
}
