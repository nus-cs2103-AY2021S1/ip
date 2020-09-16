package duke;

import java.util.List;

public class Ui {
    public static final String WELCOME = "Duke at your service!\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n\n"
            + "How can I help you?\n"
            + "Type in your orders below.\n\n"
            + "(type 'help' to show command list)";

    public static final String HELP = "List of commands:\n\n" +
            "bye:\n" +
            "saves and exits Duke\n\n" +
            "list:\n" +
            "shows tasks as numbered list\n\n" +
            "help:\n" +
            "shows the command list\n\n" +
            "done <index>:\n" +
            "checks task at <index> as done\n\n" +
            "delete <index>:\n" +
            "deletes task at <index>\n\n" +
            "find <keyword>:\n" +
            "finds tasks containing <keyword>\n\n" +
            "update:\n" +
            "changes details of a task\n\n" +
            "deadline/todo/event:\n" +
            "creates a task";

    public static final String GOODBYE = "Alright, see you soon!";
    public static final String LOADING_FAILED = "Loading failed: Duke continues with empty duke.TaskList.";
    public static final String STAR_LINE = "––––––––––––––––––––– *** –––––––––––––––––––––";

    public static void displayStarLine() {
        System.out.println(STAR_LINE);
    }

    public static void displayLoadingError() {
        displayMessage(LOADING_FAILED);
    }

    public static String displayWelcome() {
        return displayMessage(WELCOME);
    }

    public static String displayGoodbye() {
        return displayMessage(GOODBYE);
    }

    public static String displayHelp() { return displayMessage(HELP);}

    /**
     * Displays a message between two separator lines.
     *
     * @param message current {@code TaskList}
     */
    public static String displayMessage(String message) {
        displayStarLine();
        System.out.println(message);
        displayStarLine();
        System.out.println();
        return message;
    }

    /**
     * Displays the tasks in {@code TaskList} as a numbered list.
     * 
     * @param taskList current {@code TaskList}
     */
    public static String displayList(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        int pending = taskList.getNumOfPendingTasks();
        int currId = 1;
        StringBuilder toReturn = new StringBuilder("TO-DO LIST:\n"
                + String.format("%d pending", pending));

        for (Task t : tasks) {
            toReturn.append(String.format("\n   %d. %s", currId, t));
            currId++;
        }

        return displayMessage(toReturn.toString());
    }
}
