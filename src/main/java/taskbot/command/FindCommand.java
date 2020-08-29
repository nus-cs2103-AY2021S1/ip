package taskbot.command;

import java.util.Arrays;

import taskbot.task.TaskList;
import taskbot.ui.Ui;


/**
 * This command handles the find operation of TaskBot.
 */
public class FindCommand extends Command {
    /** The keyword used to match tasks. */
    private String[] keywords;

    /**
     * Creates a FindCommand.
     * @param keywords The keywords used to find tasks.
     */
    public FindCommand(String ... keywords) {
        super(false);
        for (int i = 0; i < keywords.length; i++) {
            keywords[i] = keywords[i].toLowerCase();
        }
        this.keywords = keywords;
    }

    /**
     * @return The keyword stored within this command.
     */
    public String[] getKeywords() {
        return keywords;
    }

    /**
     * Calls the given task list to find the keyword.
     * @param taskList The task list given.
     * @param ui The ui of TaskBot
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.findTasks(keywords);
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
        return Arrays.equals(keywords, ((FindCommand) obj).getKeywords());
    }
}
