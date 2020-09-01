package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Lists all tasks from the task list.
 */
public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return listAllTasks(taskList);
    }

    private String listAllTasks(TaskList taskList) {
        String response = "Here are all the tasks you currently have! \n";
        for (int i = 0; i < taskList.size(); i++) {
            try {
                String output = (i + 1) + "." + taskList.getTask(i + 1) + "\n";
                response += output;
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return response;
    }
}


