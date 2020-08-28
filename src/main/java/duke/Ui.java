package duke;

import java.util.List;

public class Ui {

    public static void displayStarLine() {
        System.out.println("––––––––––––––––––––– *** –––––––––––––––––––––");
    }

    public static void displayMessage(String message) {
        displayStarLine();
        System.out.println(message);
        displayStarLine();
    }

    public static void displayLoadingError() {
        displayMessage("Loading failed: Duke continues with empty duke.TaskList.");
    }

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

    public static void displayBye() {
        displayMessage("Alright, see you soon!");
    }

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
