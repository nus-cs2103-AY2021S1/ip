package main.java;


import java.io.IOException;

public abstract class Command {
    abstract void excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException;

    public boolean isContinuing() {
        return true;
    }
}