package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Represents an object that is responsible for user interaction.
 * This includes reading user input and displaying the correspondent output.
 */
public class Ui {

    private final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    public String showWelcome() {
        return "Hello from\n" + logo + "\nHey, I'm Emilia \u2764 !\nWhat can I do for you?\n";
    }

    public String showError(Exception e) {
        return e.getMessage();
    }

    public String showErrorLoad(String errorMessage) {
        return errorMessage + "\n" + "Initialize an empty TaskList!\n";
    }

    public String showBye() {
        return "Welcome back \u2764 !";
    }

    public String showList() {
        return "Please take a look at the tasks:";
    }

    /**
     * Represents the message displayed on GUI for a task
     * @param count the index of this task in list
     * @param task the task
     * @return String representation of the task with index
     */
    public String showTask(int count, Task task) {
        String space = ". ";
        if (count < 10) {
            space = ".   ";
        }
        return count + space + task;
    }

    public String showDone(Task task) {
        return "Understood, I've marked this task as done:\n" + task;
    }

    public String showUpdate(Task task) {
        return "Understood, I've updated this task for you:\n" + task;
    }

    /**
     * Represents the text message to be displayed upon a DeleteCommand.
     * @param task the task being deleted
     * @param list list of tasks
     * @return confirm message for delete command
     */
    public String showDelete(Task task, TaskList list) {
        return "Understood, I've deleted this task\n" + task + "\n" + "You have "
                + list.getSize() + " tasks in your list now!";
    }

    public String showCheck() {
        return "Hey! I have printed out the tasks that match the date:";
    }

    /**
     * Represents the text message to be displayed upon a AddCommand.
     * @param current task being added
     * @param list list of tasks
     * @return confirm message for AddCommand
     */
    public String showAdd(Task current, TaskList list) {
        return "Understood! I've added this task:\n" + current + "\n You have "
                + list.getSize() + " tasks in your list now!";
    }

    public String showFind() {
        return "Hey! I have printed out the tasks that match the name:";
    }

    public String showNothingFound() {
        return "Hmm, I didn't find anything that match your input";
    }

    public String showSort() {
        return "I have sorted the list of tasks! Todo tasks will be display lastly.\n";
    }

    public String showRemind() {
        return "Please take a look at the tasks that will occur soon:\n";
    }
}
