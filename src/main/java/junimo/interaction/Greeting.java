package junimo.interaction;

/**
 * Deals with basic interaction with the user.
 * Contains methods that greets user on start and exit of program.
 */
public class Greeting {
    private static final String STAR_LINE = "*******************************************************";

    /**
     * Prints welcome greeting to user at start of program.
     */
    public static String welcome() {
        String welcomeMessage = STAR_LINE
                + "\nWelcome! I am Junimo!\n"
                + "\nHere are some magic words to get you going:"
                + "\n* To add a todo, say: 'todo <task description>'."
                + "\n* To add a deadline, say:"
                + "\n       'deadline <task description> /by <yyyy-mm-dd>'."
                + "\n* To add an event, say:"
                + "\n       'event <event description> /at <event location>'."
                + "\n* To view your tasks, say: 'list'."
                + "\n* To check off a task, say: 'done <task number>'."
                + "\n* To find tasks, say: 'find <word>'."
                + "\n* To archive tasks, say: 'archive <task number>'."
                + "\n* To unarchive tasks, say: 'unarchive <task number>'."
                + "\n* To view your archived tasks, say: 'archives'.\n"
                + STAR_LINE;
        System.out.println(welcomeMessage);
        return welcomeMessage;
    }

    /**
     * Prints farewell greeting to user at end of program.
     */
    public static String exit() {
        String exitMessage = "Bye!! Hope you have a productive day ahead! :))";
        System.out.println(exitMessage);
        return exitMessage;
    }
}
