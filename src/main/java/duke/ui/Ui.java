package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Handles the program interactions with user.
 */
public class Ui {

    /**
     * Greets the user upon starting the program.
     */
    public String greetings() {
        return "Hello, I'm Duke!\nWhat can I do for you?";
    }

    /**
     * Retrieves the goodbye message.
     */
    public String goodBye() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Prints the file creation message.
     */
    public String fileCreationError() {
        return "Error in creating file.\n";
    }

    /**
     * Prints the file update error.
     */
    public String fileUpdateError() {
        return "Error in updating file\n";
    }

    /**
     * Prints the file read error when reading from the CSV file.
     */
    public String fileReadingError() {
        return "Error in reading from csv file\n";
    }

    /**
     * Prints task done message.
     *
     * @param current Input task.
     */
    public String markTaskAsDone(Task current) {
        return String.format("Nice! I've marked this task as done:\n      %s", current);
    }

    /**
     * Prints the deletion success message.
     *
     * @param current Current task.
     * @param size Size of task list.
     */
    public String deleteTask(Task current, int size) {
        return String.format("Noted. I've removed this task:\n      %s\n"
            + "Now you have %d tasks in the list.", current, size);
    }

    /**
     * Prints the add task message.
     *
     * @param newTask New task added.
     * @param size Size of task list.
     */
    public String addTask(Task newTask, int size) {
        return String.format("Got it. I've added this task:\n      %s\n"
            + "Now you have %d tasks in the list.", newTask, size);
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
            str1.append(String.format("     %d.%s\n", i + 1, tasks.get(i)));
        }
        str1.append(String.format("     %d.%s", size, tasks.get(size - 1)));
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
}
