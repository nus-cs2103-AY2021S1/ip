package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Handles the interaction with the user.
 */
public class Ui {

    /**
     * Greeting messages when bot starts up
     */
    public String start() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * Goodbye messages upon bot exits
     */
    public String exit() {
        return "Bye. Hope to see you again soon!\n" + "Program exiting in 5 seconds";
    }

    /**
     * Messages to be printed when a task is added
     *
     * @param taskList current list of tasks
     */
    public String printAddTask(TaskList taskList) {
        String content = "";
        content += "Got it. I've added this task:\n" + taskList.getTask(taskList.getSize() - 1) + "\n";
        content += "Now you have " + taskList.getSize() + " tasks in the list.";
        return content;
    }

    /**
     * Messages to be printed when a task is deleted
     *
     * @param taskList current list of tasks
     * @param task tasks that is deleted
     */
    public String printDeleteTask(TaskList taskList, Task task) {
        String content = "";
        content += "Noted. I've deleted this task:\n" + task + "\n";
        content += "Now you have " + taskList.getSize() + " tasks in the list.";
        return content;
    }

    /**
     * Messages to be printed when a task has been completed
     *
     * @param task task that has been completed
     */
    public String printDoneTask(Task task) {
        String content = "";
        content += "Nice! I've marked this task as done:\n";
        content += task.toString();
        return content;
    }

    /**
     * Messages to be printed when the user is finding a task
     */
    public String printFind() {
        return "Here are the matching tasks in your list:";
    }

    /**
     * Prints the error message
     *
     * @param error error message
     */
    public String showError(String error) {
        return error;
    }
}
