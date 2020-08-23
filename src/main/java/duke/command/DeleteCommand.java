package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.exception.DukeTaskNotFoundException;
import main.java.duke.task.Task;

/**
 * Represents a command which deletes a task.
 */
public class DeleteCommand extends Command {

    private String[] commandDetails;

    /**
     * Creates a new instance of a DeleteCommand.
     *
     * @param commandDetails String array with task details.
     */
    public DeleteCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the operation for an task to be deleted.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeTaskNotFoundException {
        int taskNumber = Character.getNumericValue(commandDetails[1].charAt(0)) - 1;
        if (!tasks.getTasks().isEmpty() && taskNumber < tasks.getTasks().size()) {
            Task removedTask = tasks.getTasks().remove(taskNumber);
            ui.showDeletedTask(removedTask, tasks.getTasks().size());
        } else {
            throw new DukeTaskNotFoundException(" ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN ");
        }
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
