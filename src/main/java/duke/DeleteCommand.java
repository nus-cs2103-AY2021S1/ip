package duke;

import java.util.ArrayList;

/**
 * Controls logic of deleting tasks.
 */
class DeleteCommand extends Command {

    /** Index to delete task. */
    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes deleting of tasks.
     *
     * @param tasks Stores task list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     * @throws DukeException When I/O error occurs.
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] taskAndTaskListInfo = tasks.deleteTask(index);
        String taskString = taskAndTaskListInfo[0];
        String lenString = taskAndTaskListInfo[1];

        ArrayList<Task> taskList = tasks.getTasks();
        storage.save(taskList);
        return ui.showDeleteTaskMessage(taskString , lenString);

    }

    /**
     * Checks if should exit program.
     *
     * @return Should not exit program.
     */
    @Override
    boolean isExit() {
        return false;
    }
}
