import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the user interface of the Duke chat bot.
 */
public class Ui {

    private String wrapMessage(String message) {
        String line = "----------------------------------------------";
        return line + "\n " + message + "\n" + line;
    }

    /**
     * Greets the user when they start up the Duke chat bot.
     */
    public void greet() {
        String greeting = "Hello! I'm Duke\n What can I do for you? (◠  ◠✿)";
        System.out.println(wrapMessage(greeting));
    }

    /**
     * Bids farewell to the user when they exit the Duke chat bot.
     */
    public void exit() {
        String byeMessage = "Bye! ( ´ ▽ ` )/";
        System.out.println(wrapMessage(byeMessage));
        System.exit(0);
    }

    /**
     * Informs the user that the task has been added to the list and the number of tasks
     * currently in the list.
     *
     * @param task the task to be added
     * @param size the number of tasks in the current list
     */
    public void addTask(Task task, int size) {
        String message = "Got it. I've added this task: \n  " + task +
                "\n Now you have " + size + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    /**
     * Informs the user that the task has been marked as done.
     *
     * @param task the task to be completed
     */
    public void completeTask(Task task) {
        String message = "Nice! I've marked this task as done: \n  " + task;
        System.out.println(wrapMessage(message));
    }

    /**
     * Informs the user that the task has been deleted and the number of tasks currently
     * in the list.
     *
     * @param task the task to be deleted
     * @param size the number of tasks in the current list
     */
    public void deleteTask(Task task, int size) {
        String message = "Noted. I've removed this task: \n " + task +
                "\n Now you have " + size + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    /**
     * Lists out all the tasks in the current task list in
     * numbered order.
     *
     * @param formattedList the formatted task list
     */
    public void list(String formattedList) {
        String list = "Here are the tasks in your list: \n  ";
        list += formattedList;
        System.out.println(wrapMessage(list));
    }

    /**
     * Informs the user of the error that has occurred while using the Duke
     * chat bot.
     *
     * @param e the error caught
     */
    public void showDukeError(DukeException e) {
        System.out.println(wrapMessage(e.toString()));
    }

    /**
     * Informs the user that an error occurred while trying to save their tasks.
     */
    public void showSaveError() {
        System.out.println(wrapMessage("Something went wrong while saving your tasks..."));
    }

    /**
     * Informs the user that an error occurred while trying to load their tasks.
     */
    public void showLoadError() {
        System.out.println(wrapMessage("Something went wrong while loading your tasks..."));
    }

    public void find(String matchingTasks) {
        String list = "Here are the matching tasks in your list: \n  ";
        list += matchingTasks;
        System.out.println(wrapMessage(list));
    }
}
