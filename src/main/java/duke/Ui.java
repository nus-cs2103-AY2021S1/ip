package duke;

import java.util.List;

public class Ui {
    public static final String WELCOME = "Duke at your service!\n"
            + " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n\n"
            + "How can I help you?\n"
            + "Type in your orders below.\n\n"
            + "(command list: 'list', 'deadline', 'event', 'todo', 'done', 'bye')";
    public static final String GOODBYE = "Alright, see you soon!";
    public static final String LOADING_FAILED = "Loading failed: Duke continues with empty duke.TaskList.";
    public static final String STAR_LINE = "––––––––––––––––––––– *** –––––––––––––––––––––";

    /**
     * Displays the separator star line.
     */
    public static String displayStarLine() {
        System.out.println(STAR_LINE);
        return STAR_LINE;

    }

    /**
     * Displays a message between two separator lines.
     *
     * @param message
     */
    public static String displayMessage(String message) {
        displayStarLine();
        System.out.println(message);
        displayStarLine();
        System.out.println();
        return message;
    }

    /**
     * Displays the loading error.
     */
    public static String displayLoadingError() {
        displayMessage(LOADING_FAILED);
        return LOADING_FAILED;
    }

    /**
     * Displays the welcome message.
     */
    public static String displayWelcome() {
        displayMessage(WELCOME);
        return WELCOME;
    }

    /**
     * Displays the goodbye message.
     */
    public static String displayGoodbye() {
        displayMessage(GOODBYE);
        return GOODBYE;
    }

    /**
     * Displays the tasks in TaskList as a numbered list.
     * 
     * @param taskList
     */
    public static String displayList(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        int pending = taskList.getNumOfPendingTasks();
        int currId = 1;
        String toReturn = "TO-DO LIST:";

        for (Task t : tasks) {
            toReturn += String.format("   %d. %s", currId, t);;
            currId++;
        }

        displayMessage(toReturn);
        return toReturn;
    }
}
