package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.StateManager;
import duke.task.Task;
import duke.task.TaskList;


public class FindCommand extends Command {

    private String[] keyWords;

    /**
     * Initializes a command with the keyword to search for in the TaskList as input.
     *
     * @param keyWords The keyword to find for in the list of tasks.
     */
    public FindCommand(String... keyWords) {
        this.keyWords = keyWords;
    }


    /**
     * Prints all tasks in the TaskList that contain the keyWord.
     *
     * @param taskList The TaskList used by Duke.
     * @param stateManager  The Storage used by Duke.
     * @return CommandResult object for ui
     * @throws DukeException
     */
    @Override
    public CommandResult execute(TaskList taskList, StateManager stateManager) throws DukeException {
        assert taskList != null && stateManager != null;

        if (taskList.numberOfTasks() > 0) {
            //ArrayList<Task> tasksWithKeyWord = taskList.find(keyWords);
            ArrayList<Task> tasksWithKeyWord = taskList.findTasks(task -> {
                    boolean hasKeyWords = true;
                    for (int j = 0; j < keyWords.length; j++) {
                        if (!task.toString().contains(keyWords[j])) {
                            hasKeyWords = false;
                            break;
                        }
                    }
                    return hasKeyWords;
                }
            );
            if (tasksWithKeyWord.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(createOpeningMessage());
                for (int i = 0; i < tasksWithKeyWord.size(); i++) {
                    Task currentTask = tasksWithKeyWord.get(i);
                    stringBuilder.append((i + 1) + ". " + currentTask.toString() + "\n");
                }

                return new CommandResult(stringBuilder.toString());
            } else {
                return new CommandResult("Sorry! No tasks with \""
                        + keyWordString() + "\" in your list.");
            }
        } else {
            return new CommandResult("There are no tasks yet!");
        }
    }

    private String createOpeningMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks with \"");
        stringBuilder.append(keyWordString());
        stringBuilder.append("\" in your list:\n");
        return stringBuilder.toString();
    }

    private String keyWordString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < keyWords.length - 1; i++) {
            stringBuilder.append(keyWords[i] + "\", \"");
        }
        stringBuilder.append(keyWords[keyWords.length - 1]);
        return stringBuilder.toString();
    }

}
