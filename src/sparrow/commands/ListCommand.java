package sparrow.commands;

import sparrow.data.task.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list: \n%s";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String tasksAsString = ui.taskListToString(tasks.getTasks());
        ui.replyToUser(String.format(MESSAGE_SUCCESS, tasksAsString));
    }
}
