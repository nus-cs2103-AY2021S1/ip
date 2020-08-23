package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class ParsedCommand {
    String commandType;
    int index;
    String name;
    String date;

    public ParsedCommand(String commandType) {
        this.commandType = commandType;
    }

    public void withIndex(int index) {
        this.index = index;
    }

    public void withName(String name) {
        this.name = name;
    }

    public void withDate(String date) {
        this.date = date;
    }

    public String getType() {
        return commandType;
    }

    public int getIndex() {
        return index;
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
}
