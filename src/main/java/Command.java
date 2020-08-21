package main.java;

public abstract class Command {
    public String[] command;

    public Command(String[] command) {
        this.command = command;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
