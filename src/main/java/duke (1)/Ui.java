package duke;

import duke.task.Task;
/**
 * Handles the interaction with the user.
 */
public class Ui {

    /**
     * Greeting messages when bot starts up
     *
     * @return String
     */
    public String start() {
        return "Hello! I'm Duke\n" + "What can I do for you?\n" + "Type \"help\" to see the list of command";
    }

    /**
     * Goodbye messages upon bot exits
     *
     * @return String
     */
    public String exit() {
        return "Bye. Hope to see you again soon!\n" + "Program exiting ...";
    }

    /**
     * Messages to be printed when a task is added
     *
     * @param taskList current list of tasks
     * @return String
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
     * @return String
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
     * @return String
     */
    public String printDoneTask(Task task) {
        String content = "";
        content += "Nice! I've marked this task as done:\n";
        content += task.toString();
        return content;
    }

    /**
     * Messages to be printed when the user is finding a task
     *
     * @return String
     */
    public String printFind() {
        return "Here are the matching tasks in your list:";
    }

    /**
     * Returns the error message
     *
     * @param error error message
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Returns the help content
     *
     * @return String
     */
    public String help() {
        String content = "";
        content += "Here is the list of command:\n\n";
        content += "todo [description] (eg. todo borrow book)\n";
        content += "deadline [description] /by [day] (eg. return book /by Sunday)\n";
        content += "event [description] /at [date and time] (eg, project meeting /at 09/09/2020 1400)\n";
        content += "list (returns list of tasks)\n";
        content += "done [number] (choose and complete a task)\n";
        content += "delete [number] (choose and delete a task)\n";
        content += "find [word] (search for matching task)\n";
        content += "bye (exits the application)\n";
        return content;
    }
}
