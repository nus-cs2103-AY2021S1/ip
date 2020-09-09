package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an input command that has been parsed into a class with accessible members.
 */
public class ParsedCommand {
    private String commandType;
    private List<Integer> indexes;
    private String name;
    private String date;

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
        if (indexes == null) {
            indexes = new ArrayList<>();
        }
        indexes.add(index);
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

    /**
     * Returns this ParsedCommand's type.
     * @return this ParsedCommand's type.
     */
    public String getType() {
        return commandType;
    }

    /**
     * Returns this ParsedCommand's index.
     * @return this ParsedCommand's index.
     */
    public List<Integer> getIndexes() {
        return indexes;
    }

    /**
     * Returns this ParsedCommand's name.
     * @return this ParsedCommand's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns this ParsedCommand's date.
     * @return this ParsedCommand's date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Creates a {@link Task} from this ParsedCommand's parameters.
     * @return a Task with this ParsedCommand's parameters.
     * @throws DukeException If this ParsedCommand's parameters can't be created into a Task.
     */
    public Task toTask() throws DukeException {
        assert name != null;

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

        return pc.getType().equals(getType()) && pc.getIndexes().equals(getIndexes())
                && pc.getName().equals(getName()) && pc.getDate().equals(getDate());
    }
}
