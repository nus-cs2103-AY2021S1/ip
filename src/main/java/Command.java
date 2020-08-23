package main.java;

import java.util.ArrayList;

public abstract class Command {

    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();

}
