import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface of the Duke application. The
 * user interface is responsible for receiving commands from and
 * displaying messages to the user.
 */
public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

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
     * Displays an error message to the user when the Duke application encounters
     * an error.
     * @param message Error message.
     */
    public String showError(String errorMessage) {
        String response = "";
        response += showLine();
        response += errorMessage + System.lineSeparator();
        response += showLine();
        return response;
    }

    /**
     * Displays a message indicating that a particular task has been marked as done.
     * @param task Task that has been marked as done.
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
     * Displays a message indicating that a new task has been added to the task list.
     * The total number of tasks in the task list after addition is also displayed.
     * @param task Task that has been added to the task list.
     * @param size Total number of tasks in the task list after addition.
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
     * Displays a message indicating that a task has been deleted from the task list.
     * The total number of tasks in the task list after deletion is also displayed.
     * @param task Task that has been deleted from the task list.
     * @param size Total number of tasks in the task list after deletion.
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
     * Displays tasks in the task list that match a keyword given by the user.
     * @param lst List of String objects representing the tasks in the task list
     *            that match the given keyword.
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
