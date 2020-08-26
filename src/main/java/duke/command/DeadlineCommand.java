package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a Deadline task to the list.
 */
public class DeadlineCommand extends Command {
    /**
     * A string array that represents the instructions for this command.
     */
    private String[] nextCommandArr;
    
    public DeadlineCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    /**
     * Adds a deadline task to the TaskList.
     * @param tasks is the list of tasks stored by Duke.
     * @param ui is the user interface to read inputs from the user and print messages.
     * @param storage deals with saving tasks into the file and loading tasks
     *                from the file.
     * @throws DukeException if the instructions for the command is insufficient or not in the proper format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.nextCommandArr.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty~");
        } else {
            try {
                String[] deadlineArr = nextCommandArr[1].split("/by");
                Deadline newDeadline = new Deadline(deadlineArr[0], deadlineArr[1].strip());
                tasks.add(newDeadline);
                ui.addTaskText(newDeadline, tasks);
            } catch (Exception e) {
                throw new DukeException("Please input a proper due date for your deadline~");
            }
        }
        
    }

    /**
     * Indicates Duke should keep running after this command is executed.
     * @return true.
     */
    @Override
    public boolean continueRunning() {
        return true;
    }
}
