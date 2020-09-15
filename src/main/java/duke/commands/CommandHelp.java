package duke.commands;

import duke.tasks.TaskList;

public class CommandHelp extends Command {

    public static final String COMMAND_STRING = "help";

    private static final String HELP_STRING = "I'm a task list manager basically! "
            + "Here are the commands you can use:\n"
            + "- list\n" + "- todo <Description>\n" + "- deadline <Description> /by <Time>\n"
            + "- event <Description> /at <Time>\n" + "- delete <index>\n"
            + "- done <index>\n" + "- find <search term>\n"
            + "- postpone <index> <amount> <time unit>\n"
            + "- advance <index> <amount> <time unit>\n"
            + "Date Time format: YYYY-MM-dd HHmm(-HHmm)\n";

    /**
     * Constructor for {@code CommandHelp}.
     *
     * @param taskList
     */
    public CommandHelp(TaskList taskList) {
        super(taskList);
    }

    @Override
    public String execute() {
        return HELP_STRING;
    }
}
