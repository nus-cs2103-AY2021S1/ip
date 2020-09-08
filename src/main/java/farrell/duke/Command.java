package main.java.farrell.duke;

public abstract class Command {
    CommandType type;

    public boolean shouldExit() {
        return false;
    };
    public abstract String execute(TaskList taskList) throws DukeException;
}
