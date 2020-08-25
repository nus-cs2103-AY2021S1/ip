package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DeleteException;
import duke.exception.DoneException;
import duke.exception.DukeException;

public class DoneCommand extends Command {

    public DoneCommand(String task) {
        super(task);
    }

    /**
     * Processes all the done command to determine the correct output.
     * @param taskList List of tasks.
     * @param ui UI of the bot.
     * @param storage Storage managing the file in hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            processDone(this.task, taskList, ui, storage);
        } catch (DoneException done) {
            System.out.println(done.getMessage());
        }
    }

    /**
     * Processes all the done command to determine the correct output.
     * @param theRest Parsed string containing task details.
     * @param taskList List containing all the task(s).
     * @param ui UI of the bot
     * @param storage Storage managing the file in hard disk.
     * @throws DoneException If user's input is incomplete or in the wrong format.
     */

    public void processDone(
            String theRest, TaskList taskList, Ui ui, Storage storage) throws DoneException {
        try {
            Integer taskNum = Integer.parseInt(theRest);
            taskList.markTaskAsDone(taskNum);
            Storage.updateData(taskList.getTasks());

        } catch (DukeException d) {
            throw new DoneException("Please enter a valid task number");
        }
    }

    /**
     *Evaluates whether this and other object if this and
     * other object is the same or of the same type and task details.
     * @param other Other object to compare.
     * @return True if this object
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DoneCommand) {
            DoneCommand doneCommand = (DoneCommand) other;
            return this.task.equals(doneCommand.getTask());
        }
        return false;
    }
}
