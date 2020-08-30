package duke.command;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * This class represents the find command.
 * When executed, the class will display all the task containing
 * a certain keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a Command to find a task with specified keyword.
     *
     * @param keyword The specified keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand. Executing this command will
     * prints out every task in the list that has the specified
     * keyword in the task's name.
     *
     * @param tasks TaskList of the current task.
     * @param ui Ui to deals with interactions with the user.
     * @param storage Storage to save the data to the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = ui.findTask(keyword, tasks);
        ui.sendMessage(message);
    }
}
