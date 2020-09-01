package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Finds all tasks that contains the keyword the user specified.
 */
public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        String response = "Here are the matching tasks in your list: \n";

        int index = 1;
        for (int i = 0; i < taskList.size(); i++) {
            try {
                // Check if user input matches any task description in list
                if (taskList.getTask(i + 1).getDescription().contains(super.command)) {
                    String message = (index) + "." + taskList.getTask(i + 1) + "\n";
                    response += message;
                    index++;
                }
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return response;
    }
}



