package duke.ui;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.MagicStrings;

/**
 * Deals with user interactions by scanning and printing.
 */
public class Ui {
    private Scanner sc;

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
        return MagicStrings.MESSAGE_WELCOME;
    }

    /**
     * Returns goodbye message string.
     *
     * @return Goodbye message.
     */
    public String printBye() {
        return MagicStrings.MESSAGE_GOODBYE;
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
            str.append(isForFind ? MagicStrings.HEADER_TASK_FIND : MagicStrings.HEADER_TASK_ALL);
        } else {
            str.append(MagicStrings.HEADER_TASK_NONE);
        }

        for (int i = 0; i < tasks.size(); i++) {
            str.append(printTask(tasks.get(i), i + 1));
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
        return MagicStrings.HEADER_TASK_DONE + task.toString();
    }

    public String printTaskDeleted(Task task) {
        return MagicStrings.HEADER_TASK_DELETE + task.toString();
    }

    public String printTaskEdited(Task task) {
        return MagicStrings.HEADER_TASK_EDIT + task.toString();
    }

    public String printTaskAdded(Task task) {
        return MagicStrings.HEADER_TASK_ADD + task.toString();
    }
}
