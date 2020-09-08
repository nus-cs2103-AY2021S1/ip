package sparrow.commands;

import sparrow.data.exceptions.FileErrorException;
import sparrow.data.task.Task;
import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
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
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        try {
            Task deletedTask = tasks.deleteTask(getTargetIndex());
            storage.saveTaskListToFile(tasks);
            return String.format(MESSAGE_DELETE_TASK_SUCCESS, deletedTask);
        } catch (IndexOutOfBoundsException e) {
            return "INDEX OUT OF BOUNDS";
        } catch (FileErrorException fee) {
            return fee.getMessage();
        }
    }
}
