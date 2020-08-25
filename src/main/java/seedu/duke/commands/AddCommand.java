package main.java.seedu.duke.commands;

import main.java.seedu.duke.todo.Task;
import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;
import main.java.seedu.duke.Storage;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        super("add");
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
        // ui.showAddMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
