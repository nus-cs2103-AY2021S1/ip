package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.task.Task;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class CompleteTaskCommand extends Command {

    final int taskIndex;

    public CompleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.completeTaskAt(this.taskIndex);
        if (task != null) {
            ui.printCompleteTask(task);
        } else {
            ui.printInvalidTaskIndex();
        }
    }
}
