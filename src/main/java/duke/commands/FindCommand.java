package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Encapsulates a command for Mrs Dino to find tasks using keywords given.
 */
public class FindCommand extends Command {
    /**
     * Whether this command is a terminal command.
     */
    private final boolean HAS_FINISHED = false;

    /**
     * The target keyword to search for relevant tasks.
     */
    private String keyword;

    /**
     * Constructs a new FindCommand.
     *
     * @param keyword The target keyword to search for relevant tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        String[] keywords = keyword.split(" ");
        // For CLI
        taskList.find(keywords);
        // For GUI
        ArrayList<Task> matchingTasks = taskList.findForGui(keywords);
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks in your list:\n");
        str.append(taskListToString(matchingTasks));
        return new CommandResult(str.toString());
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
