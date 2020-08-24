package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList tasks, Ui ui);
}
