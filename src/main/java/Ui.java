import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface of the Duke application. The user interface is
 * responsible for constructing responses to user commands.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a line display.
     * @return Line display.
     */
    public String showLine() {
        return "----------------------------------------------------------" + System.lineSeparator();
    }

    /**
     * Displays a welcome message to the user when the Duke application starts up.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays a farewell message to the user when the Duke application exits.
     */
    public void showFarewell() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Returns a response specifying an error that has occurred during the execution
     * of a user command.
     * @param errorMessage Message specifying the error encountered.
     * @return Response of the user interface.
     */
    public String showError(String errorMessage) {
        String response = "";
        response += showLine();
        response += errorMessage + System.lineSeparator();
        response += showLine();
        return response;
    }

    /**
     * Returns a response indicating that a specified task in the task list of the Duke application
     * has been marked as done.
     * @param task Task in the task list that has been marked as done.
     * @return Response of the user interface.
     */
    public String showDone(Task task) {
        String response = "";
        response += showLine();
        response += "Nice! I've marked this task as done:" + System.lineSeparator();
        response += "   " + task + System.lineSeparator();
        response += showLine();
        return response;
    }

    /**
     * Returns a response indicating that a specified task has been added to the task list
     * of the Duke application. The response also indicates the updated size of the task list.
     * @param task Task that has been added to the task list.
     * @param taskListSize Updated size of the task list.
     * @return Response of the user interface.
     */
    public String showAdd(Task task, int taskListSize) {
        String response = "";
        response += showLine();
        response += "Got it. I've added this task:" + System.lineSeparator();
        response += "    " + task + System.lineSeparator();
        response += "Now you have " + taskListSize + " tasks in the list." + System.lineSeparator();
        response += showLine();
        return response;
    }

    /**
     * Returns a response indicating that specified tasks have been deleted from the task list
     * of the Duke application. The response also indicates the updated size of the task list.
     * @param tasks ArrayList of tasks that have been deleted from the task list.
     * @param taskListSize Updated size of the task list.
     * @return Response of the user interface.
     */
    public String showDelete(ArrayList<Task> tasks, int taskListSize) {
        String response = "";
        response += showLine();
        response += "Noted. I've removed these tasks: " + System.lineSeparator();
        for (Task task : tasks) {
            response += "   " + task + System.lineSeparator();
        }
        response += "Now you have " + taskListSize + " tasks in the list." + System.lineSeparator();
        response += showLine();
        return response;
    }

    /**
     * Returns a response that displays the tasks in the task list of the Duke application.
     * @param tasks ArrayList of tasks in the task list.
     * @return Response of the user interface.
     */
    public String showList(ArrayList<Task> tasks) {
        String response = "";
        response += showLine();
        response += "Here are the tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            response += (i + 1) + "." + tasks.get(i).toString() + System.lineSeparator();
        }
        response += showLine();
        return response;
    }

    /**
     * Returns a response indicating the tasks in the task list of the Duke application
     * that match a given keyword.
     * @param tasks ArrayList of tasks in the task list that match a given keyword.
     * @return Response of the user interface.
     */
    public String showFind(ArrayList<String> tasks) {
        String response = "";
        response += showLine();
        response += "Here are the matching tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            response += "   " + (i + 1) + "." + tasks.get(i) + System.lineSeparator();
        }
        response += showLine();
        return response;
    }

    /**
     * Reads the command entered by the user.
     * @return User command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
