package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.InvalidFileException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.*;

/**
 * FindCommand class to execute command that search for tasks in the
 * task list using keywords.
 */
public class FindCommand extends Command {

    public static String TASK_FOUND_MESSAGE = "Here are the matching tasks in your list:";
    public static String NO_TASK_FOUND = "There are no matching task!";


    public FindCommand(String input) {
        super(input);
    }

    /**
     * Execute the find command to search the task list for task with similar keywords.
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidInputException incorrect input after find command.
     * @throws InvalidFileException failed to save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
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
            ui.printMessage(NO_TASK_FOUND);
        } else {
            ui.printMessage(TASK_FOUND_MESSAGE);
            for (int j = 0; j < tasksFound.size(); j++) {
                ui.printMessage((j + 1) + "." + tasksFound.get(j));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
