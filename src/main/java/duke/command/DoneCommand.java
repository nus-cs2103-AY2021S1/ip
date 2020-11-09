package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeTaskNotFoundException;

/**
 * Represents a command which marks a task as completed.
 */
public class DoneCommand extends Command {

    private String[] commandDetails;

    /**
     * Creates a new instance of a DoneCommand.
     *
     * @param commandDetails String array with task details.
     */
    public DoneCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the operation for an task to be marked as done.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed DoneCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeTaskNotFoundException {
        int taskNumber = Integer.parseInt(commandDetails[1]) - 1;
        if (tasks.getTasks().isEmpty() || taskNumber > tasks.getTasks().size()) {
            throw new DukeTaskNotFoundException(" ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN ");
        }
        tasks.getTasks().get(taskNumber).doneTask();
        return ui.showDoneTask(tasks.getTasks().get(taskNumber));
    }

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
