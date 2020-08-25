package duke;

/**
 * Show the tasklist command.
 */
public class ListCommand extends Command {

    /**
     * Execute the command.
     *
     * @param taskList the tasklist used for the command.
     * @param storage  the storage used for the command.
     * @throws DukeException duke failed to complete the command.
     */
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.showTaskList();
        storage.writeToFile(taskList);
    }
}
