package sparrow.commands;

import sparrow.data.task.Task;
import sparrow.data.task.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_DELETE_TASK_SUCCESS =
            "Jolly riddance! I've deleted this task: \n\t%s";

    public DeleteCommand(int indexToDelete) {
        super(indexToDelete);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deletedTask = tasks.deleteTask(getTargetIndex());
            storage.saveToFile(tasks);
            ui.replyToUser(String.format(MESSAGE_DELETE_TASK_SUCCESS, deletedTask));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("INDEX OUT OF BOUNDS");
        }
    }
}
