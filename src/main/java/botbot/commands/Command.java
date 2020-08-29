package botbot.commands;

import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList tasks, Ui ui);
}
