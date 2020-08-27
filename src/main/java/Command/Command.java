package main.java.Command;

import main.java.TaskList;

public abstract class Command {
    protected String commandText;
    public abstract void execute(String text, TaskList tasklist);
}
