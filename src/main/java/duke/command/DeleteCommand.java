package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DeleteException;
import duke.exception.DukeException;

public class DeleteCommand extends Command {

    public DeleteCommand(String task) {
        super(task);
    }

    /**
     * Processes all the delete command to determine the correct output.
     *
     * @param taskList List of tasks.
     * @param ui       UI of the bot.
     * @param storage  Storage managing the file in hard disk.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return processDelete(this.task, taskList, ui, storage);
        } catch (DeleteException d) {
            return d.getMessage();
        }

    }

    /**
     * Processes all the deadline command to determine the correct output.
     *
     * @param theRest  Parsed string containing task details.
     * @param taskList List containing all the task(s).
     * @param ui       UI of the bot
     * @param storage  Storage managing the file in hard disk.
     * @throws DeleteException If user's input is incomplete or in the wrong format.
     */

    public String processDelete(
        String theRest, TaskList taskList, Ui ui, Storage storage) throws DeleteException {
        try {
            Integer taskNum = Integer.parseInt(theRest);
            int index = taskNum - 1;
            Storage.updateData(taskList.getTasks());
            return taskList.deleteTask(index);
        } catch (DukeException d) {
            throw new DeleteException("Please enter a number. I cannot delete nothing :(");
        }
    }

    /**
     * Evaluates whether this and other object if this and
     * other object is the same or of the same type and task details.
     *
     * @param other Other object to compare.
     * @return True if this object
     */

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DeleteCommand) {
            DeleteCommand deleteCommand = (DeleteCommand) other;
            return this.task.equals(deleteCommand.getTask());
        }
        return false;
    }
}
