package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command to create new deadline task.
 */
public class DeadlineCommand extends Command {
    private String deadlineDetails;

    public DeadlineCommand(String deadlineDetails) {
        this.deadlineDetails = deadlineDetails;
    }

    /**
     * Creates new deadline task, adds task to TaskList then updates the Storage.
     * @param taskList the list of tasks.
     * @param ui
     * @param storage
     * @throws DukeException when task details are not specified.
     * @return the Duke response to show user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] details = this.deadlineDetails.split(" /by ", 2);
            Deadline newDeadline = new Deadline(details[0], details[1], false);
            taskList.add(newDeadline);
            storage.save(taskList);
            String response = "Got it. I've added this task: \n" + taskList.get(taskList.size() - 1) + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.";
            return response;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please specify your deadline description and details!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
