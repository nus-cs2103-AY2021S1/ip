import java.util.Scanner;

/**
 * Represents the system that interacts with the user, reading inputs and displaying messages.
 */
public class Ui {

    public static final String LINE = " ____________________________________________________________\n ";

    public static final String LOGO =
            " ____        _\n"
                    + " |  _ \\ _   _| | _____\n"
                    + " | | | | | | | |/ / _ \\\n"
                    + " | |_| | |_| |   <  __/\n"
                    + " |____/ \\__,_|_|\\_\\___|\n";

    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }


    /**
     * Show a line, which is printed before and after each message for design.
     */
    public String showLine() {
        System.out.print(LINE);
        return LINE;
    }

    /**
     * Show a welcome message to the user.
     */
    public String showWelcome() {
        this.showLine();
        String result = LOGO + " Hello! I'm Duke\n" + " What can I do for you today?";
        System.out.println(result);
        this.showLine();
        return result;
    }

    /**
     * Show bye message to the user
     * @return The string with message.
     */
    public String showBye() {
        String result = " Goodbye! See you again!";
        System.out.println(result);
        return result;
    }

    /**
     * Show the task that is added and the updated number of tasks.
     *
     * @param task Task to be added.
     * @param numTasks Updated number of tasks.
     */
    public String showAdded(Task task, int numTasks) {
        String result = "Got it. I've added this task:\n    " + task
                + "\n Now you have " + numTasks + " task(s) in the list.";
        System.out.println(result);
        return result;
    }

    /**
     * Show the task that is deleted and the updated number of tasks.
     *
     * @param task Task to be deleted.
     * @param numTasks Updated number of tasks.
     */
    public String showDeleted(Task task, int numTasks) {
        String result = "Noted. I've removed this task:\n    " + task
                + "\n Now you have " + numTasks + " task(s) in the list.";
        System.out.println(result);
        return result;
    }

    /**
     * Show the task that is marked as done.
     *
     * @param task Task that is marked as done.
     */
    public String showMarkedDone(Task task) {
        String result = "Nice! I've marked this task as done:\n    " + task;
        System.out.println(result);
        return result;
    }

    /**
     * Show the error that has occurred.
     *
     * @param msg Error message.
     */
    public String showError(String msg) {
        String result = msg;
        System.out.println(result);
        return result;
    }

    /**
     * Show a message to indicate there are no tasks currently.
     */
    public String showEmptyTaskList() {
        String result = "There are currently no tasks in your list.";
        System.out.println(result);
        return result;
    }

    /**
     * Show all tasks in the task list.
     *
     * @param tasksList Task list of all tasks.
     */
    public String showTaskList(String tasksList) {
        String result = "Task(s) in your list:\n" + tasksList;
        System.out.println(result);
        return result;
    }

    /**
     * Show message to indicate there are no matching tasks to keyword provided.
     */
    public String showEmptyMatchingList() {
        String result = "There are no matching tasks found.";
        System.out.println(result);
        return result;
    }

    /**
     * Show matching tasks to the keyword provided.
     *
     * @param matchingTaskList String of all matching tasks enumerated.
     */
    public String showMatchingTaskList(String matchingTaskList) {
        String result = "Here are the matching tasks in your list:\n" + matchingTaskList;
        System.out.println(result);
        return result;
    }

    /**
     * Reads input from user.
     *
     * @return Input of the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
