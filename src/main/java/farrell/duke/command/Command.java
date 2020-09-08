package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.TaskList;

public abstract class Command {
    CommandType type;

    public boolean shouldExit() {
        return false;
    };
    public abstract String execute(TaskList taskList) throws DukeException;
}
