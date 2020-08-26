// Todo.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import ikura.util.InvalidInputException;

/**
 * A class representing a Todo task. It has a description (name), and nothing else.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param name the Todo's description.
     */
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

    /**
     * Parses a Todo from the given input. In this case it is simply used verbatim
     * as the task's description.
     *
     * @param  input the Todo's description.
     * @return the Todo with the given description.
     * @throws InvalidInputException if the input was empty.
     */
    public static Todo parse(String input) throws InvalidInputException {
        if (input.isEmpty()) {
            throw new InvalidInputException("task description cannot be empty", getUsage());
        }

        return new Todo(input);
    }

    /**
     * Gets the usage of the todo command; this is the expected format of the input
     * passed to the parse() method.
     *
     * @return the usage.
     */
    private static String getUsage() {
        return "todo <description>";
    }
}
