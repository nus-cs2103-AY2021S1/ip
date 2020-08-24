package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

import main.java.duke.task.Task;

public class DeleteTaskCommand extends Command {

    final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.deleteTaskAt(this.taskIndex);
        if (task != null) {
            ui.printDeleteTask(tasks, task);
        } else {
            ui.printInvalidTaskIndex();
        }
    }
}
