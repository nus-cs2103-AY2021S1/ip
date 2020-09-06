package duke.ui;

import duke.tasks.Task;
import duke.tool.TaskList;

/**
 * The class deals with interactions with the user.
 */
public class Ui {
    /**
     * The formatted space.
     *
     * @return a whitespace.
     */
    public String spaceBeforeOrder() {
        return "      ";
    }


    /**
     * Print the greeting message.
     */
    public String showGreeting() {
        return "Hello! I'm Duke DuiDui\nWhat can I do for you?";
    }

    /**
     * Print the loading error message.
     */
    public void showLoadingError() {
        System.out.println("Loading error!");
    }

    /**
     * Print the message showing the task is done.
     * @param tasklist
     * @param i ith task.
     * @return
     */
    public String showDoneMessage(TaskList tasklist, int i) {
        return "Nice! I've marked this task as done:\n"
                + spaceBeforeOrder() + tasklist.getTask(i) + "\n" + spaceBeforeOrder() + "Now you have "
                + tasklist.getNumOfTasks() + " tasks in the list.";
    }

    /**
     * Print the message showing the task is deleted.
     * @param tasklist
     * @param removed the removed task.
     * @return
     */
    public String showDeleteMessage(TaskList tasklist, Task removed) {
        return "Noted. I've removed this task:\n"
                + spaceBeforeOrder() + removed + "\n" + spaceBeforeOrder() + "Now you have "
                + tasklist.getNumOfTasks() + " tasks in the list.";
    }

    /**
     * Print the message showing the task is added.
     * @param tasklist
     * @param num current number of tasks in the list.
     */
    public String showAddedMessage(TaskList tasklist, int num) {
        return "Got it. I've added this task:\n"
                + spaceBeforeOrder() + tasklist.getTask(num)
                + "\n" + spaceBeforeOrder() + "Now you have "
                + (num + 1) + " tasks in the list.";
    }

    /**
     * Print the tasks in the list.
     * @param tasklist
     * @return
     */
    public String listTasks(TaskList tasklist) {
        String output;
        output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasklist.getNumOfTasks(); i++) {
            output += spaceBeforeOrder() + (i + 1) + ". "
                    + tasklist.getTask(i) + "\n";
        }
        return output;
    }

    /**
     * Print the tasks in the list which contain the certain string.
     * @param tasklist
     * @param toFind
     * @return
     */
    public String listMatchedTasks(TaskList tasklist, String toFind) {
        String output;
        output = "Here are the matching tasks in your list:\n";
        int count = 1;
        for (int i = 0; i < tasklist.getNumOfTasks(); i++) {
            if (tasklist.getTask(i).getName().contains(toFind)) {
                output += spaceBeforeOrder() + count + ". "
                        + tasklist.getTask(i) + "\n";
                count++;
            }
        }
        return output;
    }

    /**
     * Print the goodbye message.
     * @return
     */
    public String showGoodbye() {
        return "Bye. Very nice to meet you!\n"
                + "Do remember my name is DuiDui.\n"
                + "Hope to see you again soon!";
    }
}
