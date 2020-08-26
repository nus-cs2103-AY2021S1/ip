package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyFindException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

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
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        List<Task> ls = taskList.getTasks();
        if (userInput.trim().length() <= 4) {
            throw new EmptyFindException();
        } else {
            String search = userInput.substring(5);
            List<Task> temp = new ArrayList<>();
            for (Task task : ls) {
                if (task.getDescription().contains(search)) {
                    temp.add(task);
                }
            }
            if (temp.isEmpty()) {
                System.out.println("No results found.");
            } else {
                TaskList tempList = new TaskList(temp);
                ui.printList(tempList);
            }
        }
    }
}
