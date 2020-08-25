package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command to find all tasks that contain the specified word.
 */
public class FindCommand extends Command {
    private String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Lists out the tasks in <code>tasks</code> that contain the string
     * specified by the user.
     * @param tasks List of <code>Task</code> objects.
     * @param ui Ui object created by Duke.
     * @param storage Storage object created by Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Here are the matching tasks in your list:");

        String wordToFind = userInput.substring(5);

        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.getDescription().contains(wordToFind)) {
                System.out.println("    " + (i + 1) + ". " + curr);
            }
        }
    }
}
