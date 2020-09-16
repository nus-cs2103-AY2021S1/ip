package duke.command;

import duke.main.Statement;

/**
 * a class that contains all String for the commands.
 */
public class CommandString {
    //Name of different commands.
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String CLEAR = "clear";
    public static final String UPDATE = "update";
    public static final String UPDATE_TIME = "time";
    public static final String UPDATE_DETAIL = "detail";
    public static final String HELP = "help";

    //Descriptions of different commands.
    public static final String TODO_DESCRIPTION = "Create a todo task";
    public static final String DEADLINE_DESCRIPTION = "Create a deadline task";
    public static final String EVENT_DESCRIPTION = "Create a event task";
    public static final String BYE_DESCRIPTION = "Exit the Duke program";
    public static final String LIST_DESCRIPTION = "Lists all stored tasks";
    public static final String DONE_DESCRIPTION = "Set a task as done";
    public static final String DELETE_DESCRIPTION = "Delete a task";
    public static final String FIND_DESCRIPTION = "Find tasks by inputting keywords";
    public static final String CLEAR_DESCRIPTION = "Clear all stored tasks";
    public static final String UPDATE_DESCRIPTION = "Update a task by time or detail";

    private static final String[] namesAndFunctions = new String[]{
        "[" + TODO + "] <detail>",
        "[" + DEADLINE + "] <detail> /by <when>",
        "[" + EVENT + "] <detail> /on <when>",
        "[" + BYE + "]",
        "[" + LIST + "]",
        "[" + DONE + "] <task index>",
        "[" + DELETE + "] <task index>",
        "[" + FIND + "] <keyword(s)>",
        "[" + CLEAR + "]",
        "[" + UPDATE + "] <task index> <detail/time> /to <content you want to change>"
    };

    private static final String[] descriptions = new String[]{
        TODO_DESCRIPTION, DEADLINE_DESCRIPTION, EVENT_DESCRIPTION, BYE_DESCRIPTION,
        LIST_DESCRIPTION, DONE_DESCRIPTION, DELETE_DESCRIPTION, FIND_DESCRIPTION,
        CLEAR_DESCRIPTION, UPDATE_DESCRIPTION};

    /**
     * Returns the stringBuilder of all function names and descriptions.
     *
     * @return a stringBuilder of all function names and descriptions.
     */
    public static StringBuilder showHelp() {
        String format = "%s - %s\n";
        StringBuilder result = new StringBuilder(Statement.HELP.toString());

        assert namesAndFunctions.length == descriptions.length : "check the length of names and descriptions";

        for (int i = 0; i < namesAndFunctions.length; i++) {
            result.append(String.format(format, namesAndFunctions[i], descriptions[i]));
        }
        return result;
    }
}
