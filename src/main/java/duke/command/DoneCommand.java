package duke.command;

import duke.DukeException;
import duke.History;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command when user marks a task as completed.
 */
public class DoneCommand extends Command {
    private final String userInput;

    /**
     * Constructor to create a delete command.
     *
     * @param userInput command given from user via command line.
     */
    public DoneCommand(String userInput) {
        this.userInput = userInput;

    }

    /**
     * Mark a task as completed, updates Duke's TaskList as well as Storage.
     *
     * @param taskList the List containing all the tasks that Duke has stored.
     * @param storage the database for Duke to save all tasks to the user's local storage.
     * @param history the state of all changes made to Duke's TaskList.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Storage storage, History history) throws DukeException {
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "taskList cannot be null";
        try {
            String taskIndex = userInput.substring(5);
            history.addToHistory(taskList);
            int index = Integer.valueOf(taskIndex) - 1; // taskIndex started from 1
            Task completedTask = taskList.get(index);
            completedTask.markAsDone();
            storage.updateTask(completedTask, index);
            return "Nice! I've marked this task as done:\n" + completedTask.toString();


        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me to mark as done.");
        }
    }
}
