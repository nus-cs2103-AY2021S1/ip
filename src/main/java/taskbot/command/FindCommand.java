package taskbot.command;

import java.util.Arrays;

import taskbot.task.TaskList;


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
     * Gives instructions on how to use the find command.
     * @return A string of instructions to use the command.
     */
    public static String getInstruction() {
        return "Finds tasks matching the given keywords.\n"
                + "Format: find keywords[,...]\n"
                + "keywords: the list of keywords, separated by whitespace\n"
                + "Example: keyword key1 key2";
    }

    /**
     * @return The keywords stored within this command.
     */
    public String[] getKeywords() {
        return keywords;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.findTasks(keywords);
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
