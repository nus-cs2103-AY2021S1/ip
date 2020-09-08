package duke;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Responsible for UI such as getting user input and outputting message to the user
 */
public class Ui {

    /**
     * Returns and shows error message of loading data from text file.
     */
    public String showLoadingError() {
        String message = "Failed to load data";
        System.out.println(message);
        return message;
    }

    /**
     * Returns and shows welcome message when app is started.
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
     * Returns and show goodbye message when app exits.
     */
    public String showGoodBye() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
        return message;
    }

    /**
     * Returns and shows string representation of list of tasks.
     *
     * @param taskList ArrayList containing tasks.
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
     * Returns and shows message when task is marked done.
     *
     * @param doneTask Task which is marked as done.
     */
    public String showMarkedDoneTask(Task doneTask) {
        assert doneTask.isDone : "this task is not marked as done";
        String message = "Nice! I've marked this task as done: \n"
                + doneTask.toString();
        System.out.println(message);
        return message;
    }

    /**
     * Returns and shows message when task is deleted.
     *
     * @param deletedTask Task which is deleted.
     * @param taskList ArrayList containing tasks.
     */
    public String showDeletedTask(Task deletedTask, ArrayList<Task> taskList) {
        assert !taskList.contains(deletedTask) : "this task is not deleted from taskList";
        String message = "Noted. I've removed this task: \n"
                + deletedTask.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", taskList.size());
        System.out.println(message);
        return message;
    }

    /**
     * Return and prints message when task is added.
     *
     * @param addedTask Task which is added.
     * @param taskList ArrayList containing tasks.
     */
    public String showAddedTask(Task addedTask, ArrayList<Task> taskList) {
        assert taskList.contains(addedTask) : "this task is not added to the taskList";
        String message = "Got it. I've added this task: \n"
                + addedTask.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", taskList.size());
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints how horizontal line to separate different messages.
     */
    public String showLine() {
        String message = "---------------------------------------";
        System.out.println(message);
        return message;
    }

    /**
     * Returns and prints error message.
     *
     * @param message Message of error that occurred.
     */
    public String showError(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * Returns command from user input.
     *
     * @return Command from user input.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        String userInput = "";
        userInput = scanner.nextLine(); // Read user input
        return userInput;
    }

    /**
     * Returns and prints message of find command's result.
     * @param foundTasks List of tasks of found.
     * @return Message showing tasks found by find command.
     */
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

    public String showSetPriorityOfTask(Task task) {
        String message = "Priority is added to the task as follow \n"
                + task.toString();
        System.out.println(message);
        return message;
    }
}
