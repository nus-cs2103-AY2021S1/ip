package duke;

/**
 * Shows the task list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        taskList.showTaskList(ui);
        storage.writeToFile(taskList);
    }
}
