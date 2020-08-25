package main.java;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws BobException;

    public boolean isExit() {
        return false;
    }



}
