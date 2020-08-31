package sparrow.commands;

import sparrow.data.task.Task;
import sparrow.data.task.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class AddTaskCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Aye Aye Captain! I've added this task: \n\t%s";

    private final Task toAdd;

    public AddTaskCommand(Task toAdd) {
        super();
        this.toAdd = toAdd;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(toAdd);
        storage.saveToFile(tasks);
        return String.format(MESSAGE_SUCCESS, toAdd);
    }
}
