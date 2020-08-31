package duke;

import java.util.List;

public class Ui {

    /**
     * Displays the separator star line
     */
    public static void displayStarLine() {
        System.out.println("––––––––––––––––––––– *** –––––––––––––––––––––");
    }

    /**
     * Displays a message between two separator lines
     *
     * @param message
     */
    public static void displayMessage(String message) {
        displayStarLine();
        System.out.println(message);
        displayStarLine();
    }

    /**
     * Displays the loading error
     */
    public static void displayLoadingError() {
        displayMessage("Loading failed: Duke continues with empty duke.TaskList.");
    }

    /**
     * Displays the welcome message
     */
    public static void displayWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcome = "Duke at your service!\n" +
                logo + "\n" +
                "How can I help you?\n" +
                "Type in your orders below.\n\n" +
                "(command list: 'list', 'deadline', 'event', 'todo', 'done', 'bye')";

        displayMessage(welcome);
        System.out.println();
    }

    /**
     * Displays the goodbye message
     */
    public static void displayBye() {
        displayMessage("Alright, see you soon!");
    }

    /**
     * Displays the tasks in TaskList as a numbered list
     * 
     * @param taskList
     */
    public static void displayList(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        int pending = taskList.getNumOfPendingTasks();
        int currId = 1;

        displayStarLine();
        System.out.println("TO-DO LIST:");
        System.out.println(String.format("%d pending", pending));
        for (Task t : tasks) {
            System.out.println(String.format("   %d. %s", currId, t));
            currId++;
        }
        displayStarLine();
    }
}
