package duke;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Responsible for UI such as getting user input and outputting message to the user
 */
public class Ui {

    /**
     * shows error message occured while loading data from text file
     */
    public void showLoadingError() {
        System.out.println("Failed to load data");
    }

    /**
     * show welcome message when app is started
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(
                "Hello! I'm Duke\n" +
                "What can I do for you?");
    }

    /**
     * show goodbye message when app exits
     */
    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * show list of tasks
     * @param taskList ArrayList containing tasks
     */
    public void showListOfTask(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : taskList) {
            System.out.println(String.format("%s. %s", index, task));
            index += 1;
        }
    }

    /**
     * show message when task is marked done
     * @param doneTask task which is marked as done
     */
    public void showMarkedDoneTask(Task doneTask) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(doneTask);
    }

    /**
     * show message when task is deleted
     * @param deletedTask task which is deleted
     * @param taskList ArrayList containing tasks
     */
    public void showDeletedTask(Task deletedTask, ArrayList<Task> taskList) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(deletedTask);
        System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));
    }

    /**
     * show message when task is added
     * @param addedTask task which is added
     * @param taskList ArrayList containing tasks
     */
    public void showAddedTask(Task addedTask, ArrayList<Task> taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
        System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));
    }

    /**
     * show horizontal line to saparate different messages
     */
    public void showLine() {
        System.out.println("---------------------------------------");
    }

    /**
     * show message for any error occurred
     * @param message message of error that occurred
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * read command from user input
     * @return command from user input
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String user_input = "";
        user_input = scanner.nextLine();  // Read user input
        return user_input;
    }



}
