package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.WeeklyTask;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command to create a new weekly recurring task.
 */
public class WeeklyCommand extends Command {
    private String taskDetails;

    public WeeklyCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    /**
     * Creates new weekly recurring task with date and time, adds the next instance of the task to TaskList
     * then updates the Storage.
     * @param taskList
     * @param ui
     * @param storage
     * @return the Duke response to show user
     * @throws DukeException when task description is not specified.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] details = this.taskDetails.split(" /every ", 2);
            WeeklyTask newTask = new WeeklyTask(details[0], details[1], false);
            taskList.add(newTask);
            storage.save(taskList);
            String response = "Got it. I've added this task: \n" + taskList.get(taskList.size() - 1) + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.";
            return response;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please specify your recurring task details!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
