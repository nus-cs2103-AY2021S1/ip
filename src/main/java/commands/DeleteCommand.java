package java.commands;

import java.tasks.*;
import java.tasklist.*;
import java.storage.Storage;
import java.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        Task.reduceOneTasks();
        String output = ui.displayDeleteMessage(task);
        tasks.deleteTask(index);
        return output;
    }

}
