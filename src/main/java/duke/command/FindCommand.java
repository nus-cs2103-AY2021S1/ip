package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


public class FindCommand extends Command {

    private String keyWord;

    /**
     * Initializes a command with the keyword to search for in the TaskList as input.
     *
     * @param keyWord The keyword to find for in the list of tasks.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }


    /**
     * Prints all tasks in the TaskList that contain the keyWord.
     *
     * @param taskList The TaskList used by Duke.
     * @param storage  The Storage used by Duke.
     * @return CommandResult object for ui
     * @throws DukeException
     */

    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws DukeException {
        if (taskList.numberOfTasks() > 0) {
            ArrayList<Task> tasksWithKeyWord = taskList.find(keyWord);
            if (tasksWithKeyWord.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Here are the tasks with \"" + keyWord + "\" in your list:\n");

                for (int i = 0; i < tasksWithKeyWord.size(); i++) {
                    Task currentTask = tasksWithKeyWord.get(i);
                    stringBuilder.append((i + 1) + ". " + currentTask.toString() + "\n");
                }

                return new CommandResult(stringBuilder.toString());
            } else {
                return new CommandResult("No tasks with \"" + keyWord + "\" in your list.");
            }
        } else {
            return new CommandResult("There are no tasks yet!");
        }
    }

}
