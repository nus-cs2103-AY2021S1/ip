package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.Ui;
import duke.exceptions.InvalidFileException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Task;
import duke.tasks.TaskList;


/**
 * FindCommand class to execute command that search for tasks in the
 * task list using keywords.
 */
public class FindCommand extends Command {

    private static final String TASK_FOUND_MESSAGE = "Here are the matching tasks in your list:\n";
    private static final String NO_TASK_FOUND = "There are no matching task!";


    public FindCommand(String input) {
        super(input);
    }

    /**
     * Execute the find command to search the task list for task with similar keywords.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidInputException incorrect input after find command.
     * @throws InvalidFileException failed to save file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 4) {
            throw new InvalidInputException("Please select task to find!");
        }

        String keywords = super.input.substring(5);
        List<Task> tasksFound = new ArrayList<>();
        for (Task t: tasks.getTaskList()) {
            if (t.getWordsInTask().contains(keywords)) {
                tasksFound.add(t);
            }
        }
        if (tasksFound.size() == 0) {
            return ui.printMessage(NO_TASK_FOUND);
        } else {
            String result = ui.printMessage(TASK_FOUND_MESSAGE);
            for (int j = 0; j < tasksFound.size(); j++) {
                result = result + ui.printMessage((j + 1) + "." + tasksFound.get(j)) + "\n";
            }
            return result;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
