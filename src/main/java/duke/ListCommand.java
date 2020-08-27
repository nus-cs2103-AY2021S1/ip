package duke;

/**
 * Shows the task list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.showTaskList();
        storage.writeToFile(taskList);
    }
}
