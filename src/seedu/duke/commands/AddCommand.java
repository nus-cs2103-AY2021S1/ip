package seedu.duke.commands;

import seedu.duke.todo.Task;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.Storage;

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
