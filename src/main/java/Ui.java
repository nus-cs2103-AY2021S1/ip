import java.util.Scanner;

/**
 * Represents the system that interacts with the user, reading inputs and displaying messages.
 */
public class Ui {
    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static final String LOGO =
            " ____        _\n"
                    + " |  _ \\ _   _| | _____\n"
                    + " | | | | | | | |/ / _ \\\n"
                    + " | |_| | |_| |   <  __/\n"
                    + " |____/ \\__,_|_|\\_\\___|\n";

    public static final String LINE = " ____________________________________________________________\n ";

    /**
     * Show a line, which is printed before and after each message for design.
     */
    public void showLine() {
        System.out.print(LINE);
    }

    /**
     * Show a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println(LOGO + " Hello! I'm Duke\n" + " What can I do for you today?");
        showLine();
    }

    public void showBye() {
        System.out.println(" Goodbye! See you again!");
    }

    /**
     * Show the task that is added and the updated number of tasks.
     * @param task Task to be added.
     * @param numTasks Updated number of tasks.
     */
    public void showAdded(Task task, int numTasks) {
        System.out.println("Got it. I've added this task:\n    " +
                task +
                "\n Now you have " + numTasks + " task(s) in the list.");
    }

    /**
     * Show the task that is deleted and the updated number of tasks.
     * @param task Task to be deleted.
     * @param numTasks Updated number of tasks.
     */
    public void showDeleted(Task task, int numTasks) {
        System.out.println("Noted. I've removed this task:\n    " +
                task +
                "\n Now you have " + numTasks + " task(s) in the list.");
    }

    /**
     * Show the task that is marked as done.
     * @param task Task that is marked as done.
     */
    public void showMarkedDone(Task task) {
        System.out.println(
                "Nice! I've marked this task as done:\n    " + task);
    }

    /**
     * Show the error that has occured.
     * @param msg Error message.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Show a message to indicate there are no tasks currently.
     */
    public void showEmptyTaskList() {
        System.out.println("There are currently no tasks in your list.");
    }

    /**
     * Show all tasks in the task list.
     * @param tasksList Task list of all tasks.
     */
    public void showTaskList(String tasksList) {
        System.out.println(("Task(s) in your list:\n" + tasksList));
    }

    /**
     * Reads input from user.
     * @return Input of the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
