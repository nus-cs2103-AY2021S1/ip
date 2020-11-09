package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeTaskNotFoundException;

/**
 * Represents a command which undo a task.
 */
public class UndoCommand extends Command {

    private String[] commandDetails;

    /**
     * Creates a new instance of an UndoCommand.
     *
     * @param commandDetails String array with task details.
     */
    public UndoCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the operation for an task to be undone.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed UndoCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeTaskNotFoundException {
        int taskNumber = Character.getNumericValue(commandDetails[1].charAt(0)) - 1;
        if (tasks.getTasks().isEmpty() || taskNumber > tasks.getTasks().size()) {
            throw new DukeTaskNotFoundException(" ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN ");
        }
        tasks.getTasks().get(taskNumber).undoTask();
        return ui.showUndoTask(tasks.getTasks().get(taskNumber));
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
