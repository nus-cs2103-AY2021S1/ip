package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class ParsedCommand {
    private String commandType;
    private int index;
    private String name;
    private String date;

    public ParsedCommand(String commandType) {
        this.commandType = commandType;
    }

    public ParsedCommand withIndex(int index) {
        this.index = index;
        return this;
    }

    public ParsedCommand withName(String name) {
        this.name = name;
        return this;
    }

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

    public Task toTask() throws DukeException {
        Task newTask;

        if (commandType.equals("todo")) {
            newTask = new Todo(name);
        } else if (commandType.equals("deadline")) {
            newTask = new Deadline(name, date);
        } else if (commandType.equals("event")) {
            newTask = new Event(name, date);
        } else {
            throw new DukeException("Parsed command is  not a task.");
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
