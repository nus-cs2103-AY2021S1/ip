package main.java.commands;

import main.java.commands.Command;
import main.java.tasks.*;
import main.java.tasklist.*;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        Task.reduceOneTasks();
        ui.displayDeleteMessage(task);
        tasks.deleteTask(index);
    }

}
