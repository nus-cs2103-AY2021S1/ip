package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeLoadingErrorException;
import duke.task.Task;

public class UpdateDescCommand extends UpdateCommand {

    /**
     * The new description of the task.
     */
    private String description;

    /**
     * Creates a new instance of a UpdateDescCommand with attributes defined
     * in the parameters.
     *
     * @param taskNo number of the task to be updated.
     * @param description new description of the task.
     */
    public UpdateDescCommand(int taskNo, String description) {
        super(taskNo);
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeLoadingErrorException {
        Task updatedTask = taskList.updateTaskDesc(super.taskNo, this.description);
        storage.save(taskList);
        String uiMessage = String.format(
                "Noted. I've updated this task:\n%s\n%s",
                updatedTask.toString(),
                taskList.getTaskSizeMessage());
        return ui.print(uiMessage);
    }
}
