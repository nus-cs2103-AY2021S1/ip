package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents an input command that has been parsed into a class with accessible members.
 */
public class ParsedCommand {
    String commandType;
    int index;
    String name;
    String date;

    /**
     * Constructs a base ParsedCommand with the given commandType.
     * @param commandType the intended action from the command
     */
    public ParsedCommand(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Adds an index to this ParsedCommand.
     * @param index the index to be saved to this ParsedCommand
     * @return this modified ParseCommand
     */
    public ParsedCommand withIndex(int index) {
        this.index = index;
        return this;
    }

    /**
     * Adds name to this ParsedCommand.
     * @param name the name to be saved to this ParsedCommand
     * @return this modified ParseCommand
     */
    public ParsedCommand withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Adds a date to this ParsedCommand.
     * @param date the index to be saved to this ParsedCommand
     * @return this modified ParseCommand
     */
    public ParsedCommand withDate(String date) {
        this.date = date;
        return this;
    }

    public String getType() {
        return commandType;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    /**
     * Creates a {@link Task} from this ParsedCommand's parameters.
     * @return a Task with this ParsedCommand's parameters.
     * @throws DukeException If this ParsedCommand's parameters can't be created into a Task.
     */
    public Task toTask() throws DukeException {
        Task newTask;

        if (commandType.equals("todo")) {
            newTask = new Todo(name);
        } else if (commandType.equals("deadline")) {
            newTask = new Deadline(name, date);
        } else if (commandType.equals("event")) {
            newTask = new Event(name, date);
        } else {
            throw new DukeException("Parsed command is not a task.");
        }

        return newTask;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ParsedCommand)) {
            return false;
        }

        ParsedCommand pc = (ParsedCommand) other;

        boolean a = pc.getType().equals(getType()) && pc.getIndex() == getIndex()
                && pc.getName().equals(getName()) && pc.getDate().equals(getDate());
        return pc.getType().equals(getType()) && pc.getIndex() == getIndex()
                && pc.getName().equals(getName()) && pc.getDate().equals(getDate());
    }
}
