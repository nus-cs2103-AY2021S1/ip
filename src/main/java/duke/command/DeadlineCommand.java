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
     * @return A string indicating the deadline task added.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.nextCommandArr.length >= 2) {
            try {
                String[] deadlineArr = nextCommandArr[1].split("/by");
                Deadline newDeadline = new Deadline(deadlineArr[0], deadlineArr[1].strip());
                tasks.add(newDeadline);
                return ui.addTaskText(newDeadline, tasks);
            } catch (Exception e) {
                return new DukeException("Write the date properly. (YYYY-MM-DD)").toString();
            }
        } else {
            return new DukeException("Your deadline needs a description, kid.").toString();
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
