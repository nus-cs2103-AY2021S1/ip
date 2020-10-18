/**
 * Encapsulates an ExitCommand object, used to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Says bye to the user.
     *
     * @param taskList task list containing the tasks.
     * @param ui ui used to say bye to the user.
     * @param storage storage used to store the taskList.
     * @return a string saying goodbye.
     * @throws DukeException if updateTasks fails.
     */
    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.updateTasks(taskList);
        return ui.showGoodbye();
    }

    @Override
    boolean isExit() {
        return true;
    }
}
