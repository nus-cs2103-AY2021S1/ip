package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    private String deadlineDetails;

    public DeadlineCommand(String deadlineDetails) {
        this.deadlineDetails = deadlineDetails;
    }

    /**
     * Creates new duke.task with deadline, adds duke.task to TaskList then updates the Storage.
     *
     * @param taskList the list of tasks.
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] details = this.deadlineDetails.split(" /by ", 2);
            Deadline newDeadline = new Deadline(details[0], details[1], false);
            taskList.add(newDeadline);
            String output = ui.LINE + "Got it. I've added this duke.task: \n"
                    + taskList.get(taskList.size() - 1) + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list."
                    + "\n" + ui.LINE;
            System.out.println(output);
            storage.save(taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(ui.LINE + "Invalid input! Please specify your deadline description and details! \n" + ui.LINE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
