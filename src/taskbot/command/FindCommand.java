package taskbot.command;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

/**
 * This command handles the find operation of TaskBot.
 */
public class FindCommand extends Command {
    /** The keyword used to match tasks. */
    private String keyword;

    /**
     * Creates a FindCommand.
     * @param keyword The keyword used to find tasks.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword.toLowerCase();
    }

    /**
     * @return The keyword stored within this command.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Calls the given task list to find the keyword.
     * @param taskList The task list given.
     * @param ui The ui of TaskBot
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.findTasks(keyword);
    }

    @Override
    public boolean equals(Object obj) {
        //Checks if obj is compared with itself
        if (obj == this) {
            return true;
        }

        //Checks if obj is an instance of this class
        if (!(obj instanceof FindCommand)) {
            return false;
        }

        //Compares keywords and return accordingly
        return keyword.equals(((FindCommand) obj).getKeyword());
    }
}
