package botbot.ui;

import botbot.tasks.Task;
import botbot.tasks.TaskList;

/**
 * Represents the UI of Botbot.
 */
public class Ui {
    public static final String INDENT = "  ";
    private static final String PLURALITY_PLURAL = "s";
    private static final String PLURALITY_SINGULAR = "";
    
    private static final String LOGO = "\n.-. .-')                .-') _  .-. .-')                .-') _\n"
            + "\\  ( OO )              (  OO) ) \\  ( OO )              (  OO) )\n"
            + " ;-----.\\  .-'),-----. /     '._ ;-----.\\  .-'),-----. /     '._\n"
            + " | .-.  | ( OO'  .-.  '|'--...__)| .-.  | ( OO'  .-.  '|'--...__)\n"
            + " | '-' /_)/   |  | |  |'--.  .--'| '-' /_)/   |  | |  |'--.  .--'\n"
            + " | .-. `. \\_) |  | |  |   |  |   | .-. `. \\_) |  | |  |   |  |\n"
            + " | |  \\  |  \\ |  | |  |   |  |   | |  \\  |  \\ |  | |  |   |  |\n"
            + " | '--'  /   `'  '-'  '   |  |   | '--'  /   `'  '-'  '   |  |\n"
            + " `------'      `-----'    `--'   `------'      `-----'    `--'\n";
    
    private static final String RESPONSE_ADD = "ok! I've added this task:\n" + INDENT
            + "%s\nyou now have %d task%s in your list\n";
    private static final String RESPONSE_DELETE = "ok! I've removed this task:\n" + INDENT
            + "%s\nyou now have %d task%s in your list\n";
    private static final String RESPONSE_EDIT = "ok! I've edited the task to:\n" + INDENT + "%s";
    private static final String RESPONSE_ERROR = "oops! %s\n";
    private static final String RESPONSE_EXIT = "bye! see you soon!";
    private static final String RESPONSE_FIND_MATCH = "here is the match in your list:\n%s";
    private static final String RESPONSE_FIND_MATCHES = "here are the matches in your list:\n%s";
    private static final String RESPONSE_FIND_NO_MATCH = "there are no matches in your list!\n";
    private static final String RESPONSE_GREET = "helluu! I'm\n" + LOGO
            + "\nwhat would you like me to do?\n";
    private static final String RESPONSE_LIST = "here's your list:\n%s";
    private static final String RESPONSE_LIST_EMPTY = "your list is empty!\n";
    private static final String RESPONSE_MARK_AS_DONE = "nice! I've marked this task as done:\n"
            + INDENT + "%s\n";

    /**
     * Displays the welcome message.
     *
     * @return Welcome message.
     */
    public static String greet() {
        return RESPONSE_GREET;
    }

    /**
     * Displays the exit message.
     *
     * @return Exit message.
     */
    public static String exit() {
        assert false : "System not terminated successfully";
        return RESPONSE_EXIT;
    }
    
    static String makePlural(int count) {
        return (count > 1) ? PLURALITY_PLURAL : PLURALITY_SINGULAR;
    }

    /**
     * Displays the response for an AddCommand.
     *
     * @param task Task added.
     * @param numOfTasks Updated number of tasks in task list.
     * @return Response for AddCommand.
     */
    public String showAddResponse(Task task, int numOfTasks) {
        String plurality = makePlural(numOfTasks);
        return String.format(RESPONSE_ADD, task, numOfTasks, plurality);
    }

    /**
     * Displays the response for a DeleteCommand.
     *
     * @param task Task deleted.
     * @param numOfTasks Updated number of tasks in task list.
     * @return Response for DeleteCommand.
     */
    public String showDeleteResponse(Task task, int numOfTasks) {
        String plurality = makePlural(numOfTasks);
        return String.format(RESPONSE_DELETE, task, numOfTasks, plurality);
    }

    /**
     * Displays the response for an EditCommand.
     *
     * @param task Task edited.
     * @return Response for EditCommand.
     */
    public String showEditResponse(Task task) {
        return String.format(RESPONSE_EDIT, task);
    }

    /**
     * Displays the error message.
     *
     * @param errorMessage Error message to be displayed.
     * @return Error message.
     */
    public String showErrorResponse(String errorMessage) {
        return String.format(RESPONSE_ERROR, errorMessage);
    }

    /**
     * Displays the response for a FindCommand.
     *
     * @param tasks Task list of matches.
     * @return Response for FindCommand.
     */
    public String showFindResponse(TaskList tasks) {
        if (tasks.isEmpty()) {
            return RESPONSE_FIND_NO_MATCH;
        } else if (tasks.size() == 1) {
            return String.format(RESPONSE_FIND_MATCH, tasks);
        }
        return String.format(RESPONSE_FIND_MATCHES, tasks);
    }

    /**
     * Displays the response for a ListCommand.
     *
     * @param tasks Current task list.
     * @return Response for ListCommand.
     */
    public String showListResponse(TaskList tasks) {
        if (tasks.size() <= 0) {
            return RESPONSE_LIST_EMPTY;
        }
        return String.format(RESPONSE_LIST, tasks);
    }

    /**
     * Displays the response for a MarkAsDoneCommand.
     *
     * @param task Task marked as done.
     * @return Response for MarkAsDoneCommand.
     */
    public String showMarkAsDoneResponse(Task task) {
        return String.format(RESPONSE_MARK_AS_DONE, task);
    }
}
