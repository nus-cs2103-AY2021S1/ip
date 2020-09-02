package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Handles the program interactions with user.
 */
public class Ui {

    private String stringFormatter(String... args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length - 1; i++) {
            sb.append(args[i]).append("\n");
        }
        sb.append(args[args.length - 1]);
        return sb.toString();
    }

    /**
     * Greets the user upon starting the program.
     */
    public String greetings() {
        return stringFormatter("Hello, I'm Duke!", "What can I do for you?");
    }

    /**
     * Retrieves the goodbye message.
     */
    public String goodBye() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Prints task done message.
     *
     * @param current Input task.
     */
    public String markTaskAsDone(Task current) {
        return stringFormatter("Nice! I've marked this task as done:", String.format("    %s", current));
    }

    /**
     * Prints the deletion success message.
     *
     * @param current Current task.
     * @param size Size of task list.
     */
    public String deleteTask(Task current, int size) {
        return stringFormatter("Noted. I've removed this task:", String.format("    %s", current),
            String.format("Now you have %d tasks in the list.", size));
    }

    /**
     * Prints the add task message.
     *
     * @param newTask New task added.
     * @param size Size of task list.
     */
    public String addTask(Task newTask, int size) {
        return stringFormatter("Got it. I've added this task:", String.format("    %s", newTask),
            String.format("Now you have %d tasks in the list.", size));
    }

    /**
     * Prints the empty task list message.
     */
    public String emptyTaskList() {
        return "You currently have no tasks in the list.";
    }

    /**
     * Prints the task list to the user.
     *
     * @param tasks Task list.
     * @param extra Extra word to add in, if any.
     */
    public String showTaskList(TaskList tasks, String extra) {
        StringBuilder str1 = new StringBuilder();
        str1.append(String.format("Here are the %s tasks in your list:\n", extra));
        int size = tasks.size();
        for (int i = 0; i < size - 1; i++) {
            str1.append(String.format("   %d.%s\n", i + 1, tasks.get(i)));
        }
        str1.append(String.format("   %d.%s", size, tasks.get(size - 1)));
        return str1.toString();
    }

    /**
     * Prints the no matching tasks found message.
     *
     * @param queryWord Word use to query task list.
     */
    public String emptyFind(String queryWord) {
        return String.format("There are no matching tasks with the keyword %s.", queryWord);
    }

    /**
     * Prints the file read error when reading from the CSV file.
     */
    public void fileReadingError() {
        System.out.println("Error in reading from csv file");
    }
}
