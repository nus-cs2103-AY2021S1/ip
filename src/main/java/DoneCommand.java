import java.util.ArrayList;

/**
 * Controls logic of completing tasks.
 */
public class DoneCommand extends Command {

    /** Index of task to be done. */
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }


    /**
     * Executes completing tasks.
     *
     * @param tasks Stores task list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     * @throws DukeException When I/O error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = tasks.doTask(index);
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
    public boolean isExit() {
        return false;
    }
}
