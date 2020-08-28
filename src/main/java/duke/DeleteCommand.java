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
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = tasks.deleteTask(index);
        ArrayList<Task> taskList = tasks.getTasks();
        ui.printOutput(output, true);
        storage.save(taskList);
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
