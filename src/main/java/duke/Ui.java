package duke;

import java.util.Scanner;

import exception.DukeException;
import task.Task;

/**
 * Class that handles interactions with the user
 */
public class Ui {
    private String line = "____________________________________________________________";
    private String welcomeMessage = "Hello! I'm Duke\n What can I do for you?";
    private String goodbyeMessage = "Bye! Message me anytime!!";
    private Scanner sc = new Scanner(System.in);

    public void showLine() {
        System.out.println(this.line);
    }

    public String getWelcomeMessage() {
        return this.welcomeMessage;
    }

    public String showGoodbye() {
        return this.goodbyeMessage;
    }

    public String showDone(Task task) {
        return String.join("\n", "Nice! I've marked this task as done: ", task.toString());
    }

    public String showAdd(Task task, int size) {
        return String.join("\n", "Added task:", task.toString(), showTaskQty(size));
    }

    public String showTaskQty(int size) {
        return String.format("You have %s remaining tasks left, work on them soon!", size);
    }

    public String showDelete(Task task, int size) {
        return String.join("\n", "Deleting task.Task: ", task.toString(), showTaskQty(size));
    }

    public void showError(String errMsg) {
        System.out.println(errMsg);
    }

    /**
     * Returns a string displaying the list of tasks
     * @param taskList Object of task list class
     * @return String containing information of tasks
     */
    public String displayTaskList(TaskList taskList) {
        StringBuilder s = new StringBuilder("Here are your current tasks:\n");
        for (int i = 0; i < taskList.getTaskList().size(); i++) {
            s.append(i + 1).append(".").append(taskList.getTaskList().get(i)).append("\n");
        }
        return s.toString();
    }

    /**
     * Uses the Java Scanner to read User Input and trims it
     *
     * @return String containing the trimmed user input
     * @throws DukeException When the user input given is invalid
     */
    public String readCommand() throws DukeException {
        String line = sc.nextLine();
        if (line != null) {
            return line.trim();
        } else {
            throw new DukeException("Please type in a command!");
        }
    }

    /**
     * Function to display matching tasks
     * @param filteredTaskList TaskList containing already filtered task objects
     * @return String containing information of matching tasks
     */
    public String displayMatchingTasks(TaskList filteredTaskList) {

        if (filteredTaskList.getSize() > 0) {
            StringBuilder s = new StringBuilder("Here are the matching tasks in your list\n");

            for (int i = 0; i < filteredTaskList.getTaskList().size(); i++) {
                s.append(i + 1).append(".").append(filteredTaskList.getTaskList().get(i)).append("\n");
            }
            return s.toString();
        } else {
            return "You have no matching tasks!";
        }
    }

    /**
     * Method to print welcome Message
     */
    public void showWelcome() {
        System.out.println(line);
        System.out.println(this.welcomeMessage);
        System.out.println(line);
    }
}
