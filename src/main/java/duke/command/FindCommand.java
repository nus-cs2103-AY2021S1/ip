package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
	String keyWord;

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
	 * @param taskList The TaskList used by Duke.
	 * @param ui The Ui used by Duke.
	 * @param storage The Storage used by Duke.
	 * @throws DukeException
	 */
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
		if (taskList.numberOfTasks() > 0) {
			ArrayList<Task> tasksWithKeyWord = taskList.find(keyWord);
			if (tasksWithKeyWord.size() > 0) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Here are the tasks with \"" + keyWord + "\" in your list:\n");

				for (int i = 0; i < tasksWithKeyWord.size(); i++) {
					Task currentTask = tasksWithKeyWord.get(i);
					stringBuilder.append((i + 1) + ". " + currentTask.toString() + "\n");
				}

				ui.printMessage(stringBuilder.toString());
			} else {
				ui.printMessage("No tasks with \"" + keyWord + "\" in your list.");
			}
		} else {
			ui.printMessage("There are no tasks yet!");
		}
	}
}
