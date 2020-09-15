package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyFindException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represent a command that search for tasks based on user's input.
 */
public class FindCommand extends UserCommand {
    /**
     * @param userInput user's input.
     */
    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * @param taskList task list containing all the tasks.
     * @param ui       ui that prints output.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        if (userInput.trim().length() <= 4) {
            throw new EmptyFindException();
        } else {
            String search = userInput.substring(5);
            List<Task> searchList = new ArrayList<>();
            for (int i = 0; i < taskList.listSize(); i++) {
                Task task = taskList.getTask(i);
                if (task.getDescription().contains(search)) {
                    searchList.add(task);
                }
            }
            if (searchList.isEmpty()) {
                return ui.printResponse("No results found.");
            } else {
                TaskList tempList = new TaskList(searchList);
                return ui.printList(tempList);
            }
        }
    }
}
