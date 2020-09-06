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
    public String execute(TaskList tasks, Storage storage) {
        String response = "Here are the matching tasks in your list: \n";

        for (int i = 0; i < tasks.size(); i++) {
            try {
                // Check if user input matches any task description in list
                if (tasks.getTask(i + 1).getDescription().contains(super.command)) {
                    String message = (i + 1) + "." + tasks.getTask(i + 1) + "\n";
                    response += message;
                }
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        assert response != "" : "Response is an empty string";
        return response;
    }
}



