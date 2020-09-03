package duke.ui;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with user interactions by scanning and printing.
 */
public class Ui {
    private Scanner sc;
    private static final String MESSAGE_WELCOME = "Hello! I'm Clara! :D How may I help you? :)\n";
    private static final String MESSAGE_GOODBYE = "Bye! Have a great day and hope to see you soon! :D\n";
    private static final String HEADER_ERROR = "Apologies!\n";
    private static final String HEADER_TASK_FIND = "These are the tasks that you are looking for.:)\n";
    private static final String HEADER_TASK_ALL = "These are the tasks in your list. Jiayous! :)\n";
    private static final String HEADER_TASK_NONE = "You have no task in your list. :D\n";
    private static final String HEADER_TASK_ADD = "Okay! I've added this task:";

    private static final String HEADER_TASK_DONE = "Nice! I've marked this task as done:\n\t\t";
    private static final String HEADER_TASK_DELETE= "Okay! I've removed this task:\n\t\t";
    private static final String MESSAGE_TASK_UPDATE = "This is your updated tasks. Jiayous! :D";
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the command of the user.
     * @return user's command.
     */
    public String getCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints to the console with a tab.
     * @param message Message to be printed.
     */
    public void printToConsole(String message) {
        System.out.println("\t" + message);
    }

    /**
     * Returns welcome message string.
     *
     * @return Welcome message.
     */
    public String printHello() {
        return MESSAGE_WELCOME;
    }

    /**
     * Returns goodbye message string.
     *
     * @return Goodbye message.
     */
    public String printBye() {
        return MESSAGE_GOODBYE;
    }

    /**
     * Returns list of tasks string.
     *
     * @param taskList List of tasks to be printed.
     * @param isForFind Is a keyword-matched task list.
     * @return Display-tasks response message.
     */
    public String printTasks(TaskList taskList, boolean isForFind) {
        List<Task> tasks = taskList.getTasks();
        StringBuilder str = new StringBuilder();

        if (tasks.size() > 0) {
            str.append(isForFind ? HEADER_TASK_FIND : HEADER_TASK_ALL);
        } else {
            str.append(HEADER_TASK_NONE);
        }

        for (int i = 0; i < tasks.size(); i++) {
            str.append(printTask(tasks.get(i), i+1));
        }
        return str.toString();
    }

    /**
     * Returns task string.
     *
     * @param task Task to be printed.
     * @param nth Order of task in the task list.
     * @return Task string.
     */
    public String printTask(Task task, int nth) {
        return String.format("\t%d. %s\n", nth, task.toString());
    }

    public String printTaskAsDone(Task task) {
        return HEADER_TASK_DONE + task.toString();
    }

    public String printTaskDeleted(Task task) {
        return HEADER_TASK_DELETE + task.toString();
    }

    public String printTaskAdded(Task task) {
        return HEADER_TASK_ADD + task.toString();
    }
}
