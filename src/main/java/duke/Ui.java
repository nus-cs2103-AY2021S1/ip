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

    public static void displayStarLine() {
        System.out.println(STAR_LINE);
    }

    public static void displayLoadingError() {
        displayMessage(LOADING_FAILED);
    }

    public static void displayWelcome() {
        displayMessage(WELCOME);
    }

    public static String displayGoodbye() {
        return displayMessage(GOODBYE);
    }

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
