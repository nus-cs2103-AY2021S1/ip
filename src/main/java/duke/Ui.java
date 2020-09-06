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
    public String showLoadingError() {
        String message = "Failed to load data";
        System.out.println(message);
        return message;
    }

    /**
     * show welcome message when app is started
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String message = logo
                + "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(message);
        return message;
    }

    /**
     * show goodbye message when app exits
     */
    public String showGoodBye() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
        return message;
    }

    /**
     * show list of tasks
     * @param taskList ArrayList containing tasks
     */
    public String showListOfTask(ArrayList<Task> taskList) {
        String message = "Here are the tasks in your list: \n";
        int index = 1;
        for (Task task : taskList) {
            message += String.format("%s. %s \n", index, task);
            index += 1;
        }
        System.out.println(message);
        return message;
    }

    /**
     * show message when task is marked done
     * @param doneTask task which is marked as done
     */
    public String showMarkedDoneTask(Task doneTask) {
        String message = "Nice! I've marked this task as done: \n"
                + doneTask.toString();
        System.out.println(message);
        return message;
    }

    /**
     * show message when task is deleted
     * @param deletedTask task which is deleted
     * @param taskList ArrayList containing tasks
     */
    public String showDeletedTask(Task deletedTask, ArrayList<Task> taskList) {
        String message = "Noted. I've removed this task: \n"
                + deletedTask.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", taskList.size());
        System.out.println(message);
        return message;
    }

    /**
     * show message when task is added
     * @param addedTask task which is added
     * @param taskList ArrayList containing tasks
     */
    public String showAddedTask(Task addedTask, ArrayList<Task> taskList) {
        String message = "Got it. I've added this task: \n"
                + addedTask.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", taskList.size());
        System.out.println(message);
        return message;
    }

    /**
     * show horizontal line to saparate different messages
     */
    public String showLine() {
        String message = "---------------------------------------";
        System.out.println(message);
        return message;
    }

    /**
     * show message for any error occurred
     * @param message message of error that occurred
     */
    public String showError(String message) {
        System.out.println(message);
        return message;
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

    public String showFoundTask(ArrayList<Task> foundTasks) {
        String message = "Here are the matching tasks in your list: \n";
        int index = 1;
        for (Task task : foundTasks) {
            message += (String.format("%s. %s \n", index, task));
            index += 1;
        }
        System.out.println(message);
        return message;
    }
    
}
