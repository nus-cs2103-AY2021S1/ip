// Todo.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import ikura.util.InvalidInputException;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Todo)
            && ((Todo) other).getName().equals(this.getName());
    }

    public static Todo parse(String input) throws InvalidInputException {
        if (input.isEmpty()) {
            throw new InvalidInputException("task description cannot be empty", getUsage());
        }

        return new Todo(input);
    }

    private static String getUsage() {
        return "todo <description>";
    }
}
