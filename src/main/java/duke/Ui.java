package duke;

import duke.task.Task;
import java.util.List;

/**
 * Interacts with the user by outputting certain responses
 * based on user input
 */
public class Ui {
    private final static String line = "______________________________________________________";
    private final static String lineIndent = "    ";
    private final static String listIndent = "       ";
    private final static String textIndent = "     ";

    /**
     * Displays a welcome message when the user first starts up the program.
     */
    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(String.format("Hello from\n%s\nWhat can I do for you?",
                logo));
    }

    /**
     * Displays a formatted message for the user.
     * @param message the message displayed to the user
     */
    public void printMessage(String message) {
        System.out.println(String.format("%s%s\n%s%s\n%s%s", lineIndent, line,
                textIndent, message, lineIndent, line));
    }

    /**
     * Lists down all the tasks the user has
     * @param taskList the current tasks the user has
     */
    public void listTasks(List<Task> taskList) {
        String output = "Here are the tasks in your list:\n";
        int taskLen = taskList.size();
        for (int i = 0; i < taskLen; i++) {
            output += String.format("%s%d. %s", textIndent, i + 1, taskList.get(i));
            if (i != taskLen - 1) {
                output += "\n";
            }
        }
        printMessage(output);
    }

    /**
     * Informs the user when he has successfully marked a task as complete.
     * @param task the task completed by user
     */
    public void completeSuccess(Task task) {
        printMessage(String.format("Nice! I've marked this task as done:\n%s%s",
                listIndent, task));
    }

    /**
     * Informs the user when he has successfully deleted a task, along with a count of current tasks.
     * @param task the task deleted by user
     * @param taskCount the current number of tasks the user has
     */
    public void deleteSuccess(Task task, int taskCount) {
        String message = String.format("Noted. I've removed this task:\n%s%s\n%sNow you have %d %s in the list.",
                listIndent, task, textIndent, taskCount, taskCount > 1 ? "tasks" : "task");
        printMessage(message);
    }

    /**
     * Informs the user when he has successfully added a task, along with a count of current tasks.
     * @param task the task added by user
     * @param taskCount the current number of tasks the user has
     */
    public void addSuccess(Task task, int taskCount) {
        String message = String.format("Got it. I have added this task:\n%s%s\n%sNow you have %d %s in the list.",
                listIndent, task, textIndent, taskCount, taskCount > 1 ? "tasks" : "task");
        printMessage(message);
    }

    /**
     * Lists all the tasks the user has that are on a specified date.
     * @param taskList the list of tasks on a specified date
     * @param dateString the queried date
     */
    public void listTasksWithDate(List<Task> taskList, String dateString) {
        String output = String.format("Here are the tasks with the date %s:\n", dateString);
        int counter = 1;
        for (Task task : taskList) {
            output += String.format("%s%d. %s\n", textIndent, counter, task);
            counter++;
        }
        printMessage(output);
    }
}
