package duke.ui;

import java.util.Scanner;

import duke.tasks.Task;

/**
 * Represents the system that interacts with the user, reading inputs and displaying messages.
 */
public class Ui {

    private static final String LINE = " ____________________________________________________________\n ";
    private static final String WELCOME_MESSAGE = "Hello there!\n"
            + "What can I do for you today? \nType 'help' to find out more!";
    private static final String BYE_MESSAGE = "Goodbye! Come back soon!";
    private static final String EMPTY_TASK_LIST_MESSAGE = "You have no tasks in your list. Add some tasks!";
    private static final String NO_MATCHING_TASK_MESSAGE = "Hmm.. there are no tasks with that keyword!";
    private static final String HELP_MESSAGE = "Here are the things that I can do for you! \n\n"
            + "- Add a todo task:\n"
            + "  todo <task description>\n\n"
            + "- Add an event task:\n"
            + "  event <task description> /at <task timing (yyyy-mm-dd HH:MM)>\n\n"
            + "- Add a deadline task:\n"
            + "  deadline <task description> /by <task timing (yyyy-mm-dd HH:MM)>\n\n"
            + "- See list of tasks:\n"
            + "  list\n\n"
            + "- See upcoming list of tasks:\n"
            + "  upcoming\n\n"
            + "- Mark a task as done:\n"
            + "  done <number>\n\n"
            + "- Delete a task:\n"
            + "  delete <number>\n\n"
            + "- Find tasks with a keyword:\n"
            + "  find <keyword>\n\n"
            + "- End this program :d :\n"
            + "  bye";
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
     * @return The string with welcome message.
     */
    public String showWelcome() {
        this.showLine();
        System.out.println(WELCOME_MESSAGE);
        this.showLine();
        return WELCOME_MESSAGE;
    }

    /**
     * Shows bye message to the user
     *
     * @return The string with bye message.
     */
    public String showBye() {
        System.out.println(BYE_MESSAGE);
        return BYE_MESSAGE;
    }

    /**
     * Shows the task that is added and the updated number of tasks.
     *
     * @param task Task to be added.
     * @param numTasks Updated number of tasks.
     * @return A string message showing the added task.
     */
    public String showAdded(Task task, int numTasks) {
        String result = "Got it. I've added this task:\n    " + task
                + "\n Now you have " + numTasks + " task(s) in the list.";
        System.out.println(result);
        return result;
    }

    /**
     * Shows the task that is deleted and the updated number of tasks.
     *
     * @param task Task to be deleted.
     * @param numTasks Updated number of tasks.
     * @return A string message showing the deleted task.
     */
    public String showDeleted(Task task, int numTasks) {
        String result = "No problem. I've removed this task:\n    " + task
                + "\n Now you have " + numTasks + " task(s) in the list.";
        System.out.println(result);
        return result;
    }

    /**
     * Shows the task that is marked as done.
     *
     * @param task Task that is marked as done.
     * @return A string message showing the task that is marked done.
     */
    public String showMarkedDone(Task task) {
        String result = "Ok! I've marked this task as done:\n    " + task;
        System.out.println(result);
        return result;
    }

    /**
     * Shows the error that has occurred.
     *
     * @param msg Error message.
     * @return A string message showing the error message.
     */
    public String showError(String msg) {
        String result = msg;
        System.out.println(result);
        return result;
    }

    /**
     * Shows a message to indicate there are no tasks currently.
     * @return A string message showing that there are no tasks.
     */
    public String showEmptyTaskList() {
        System.out.println(EMPTY_TASK_LIST_MESSAGE);
        return EMPTY_TASK_LIST_MESSAGE;
    }

    /**
     * Shows all tasks in the task list.
     *
     * @param tasksList Task list of all tasks.
     * @return A string message showing tasks in the list in order.
     */
    public String showTaskList(String tasksList) {
        String result = "Task(s) in your list:" + tasksList;
        System.out.println(result);
        return result;
    }

    /**
     * Shows message to indicate there are no matching tasks to keyword provided.
     */
    public String showEmptyMatchingList() {
        System.out.println(NO_MATCHING_TASK_MESSAGE);
        return NO_MATCHING_TASK_MESSAGE;
    }

    /**
     * Shows matching tasks to the keyword provided.
     *
     * @param matchingTaskList String of all matching tasks enumerated.
     * @return A string message showing matching tasks numbered in order.
     */
    public String showMatchingTaskList(String matchingTaskList) {
        String result = "Found some matching tasks:" + matchingTaskList;
        System.out.println(result);
        return result;
    }

    /**
     * Shows description of all commands.
     *
     * @return A string message showing list of commands.
     */
    public String showHelp() {
        System.out.println(HELP_MESSAGE);
        return HELP_MESSAGE;
    }

    /**
     * Shows tasks that are upcoming in a week, or message to indicate if there are no upcoming tasks.
     *
     * @param upcomingTasks The tasks that are upcoming, enumerated.
     * @return A string message showing upcoming tasks.
     */
    public String showUpcomingTasks(String upcomingTasks) {
        if (upcomingTasks.isEmpty()) {
            return "There are no upcoming tasks! Type 'list' to see all tasks.";
        } else {
            return "Here are the upcoming tasks (in a week)!" + upcomingTasks;
        }
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
