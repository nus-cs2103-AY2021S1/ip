package main.java;

public abstract class Command {
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public Command() { };

    public boolean isExit() {
        return false;
    }
}