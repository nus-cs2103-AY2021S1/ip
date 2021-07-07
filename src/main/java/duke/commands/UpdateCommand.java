package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Marks the specified task to be done or not.
 */
public class UpdateCommand extends Command {
    private int index;

    /**
     * Creates an update command to mark the task at the specified index as done.
     *
     * @param command is the user input.
     * @param index   is the index of the task in the list to be updated.
     */
    public UpdateCommand(String command, String index) {
        super(command);
        this.index = Integer.parseInt(index);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (super.command.equals("done")) {
            return setDoneStatusMessage(tasks);
        } else {
            return setPriorityMessage(tasks);
        }
    }

    public String setDoneStatusMessage(TaskList tasks) {
        String response = "Nice! I've marked this task as done:\n";
        try {
            Task selectedTask = tasks.getTask(index);
            selectedTask.setDone();
            response += selectedTask.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
        assert response != "" : "Response is an empty string";
        return response;
    }

    public String setPriorityMessage(TaskList tasks) {
        String response = "The priority of this task has been updated: \n";
        try {
            Task selectedTask = tasks.getTask(index);
            selectedTask.setPriority(super.command);
            response += selectedTask.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
        assert response != "" : "Response is an empty string";
        return response;
    }


}
