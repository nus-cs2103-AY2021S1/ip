package duke.command;

import duke.component.*;
import duke.task.Task;

/**
 * Command that adds a task to the task list and storage
 */
public class AddCommand implements Command {
    private Task newTask;

    /**
     * Initializes an AddCommand with the given new Task
     * @param newTask the new task given
     */
    public AddCommand(Task newTask) { this.newTask = newTask; }

    /**
     * Returns whether this is an exit command
     * @return false at all times
     */
    public boolean isExit() { return false; }

    /**
     * Executes the AddCommand to add the new task to the given task list and storage,
     * and print the response using the given ui
     *
     * @param taskList the task list to be updated
     * @param ui the ui that handles inputs and outputs
     * @param storage the permanent storage of task list
     * @throws DukeException if the task list cannot be saved to the storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui = new Ui();
        taskList.add(newTask);
        storage.saveList(taskList);
        ui.giveResponse("Got it. I've added this task:\n       " +
                newTask +
                "\n\t Now you have " + taskList.size() +
                " task" + (taskList.size() > 1 ? "s" : "") + " in the list.");
    }

}
