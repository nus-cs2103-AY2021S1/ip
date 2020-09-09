import java.io.IOException;
import java.util.Scanner;

/**
 * Ui class that deals with interactions with the user.
 */
public class Ui {

    public static String horizontal = "________________________________" + "\n";

    /**
     * Constructor that creates a Ui object, with a Scanner that reads user input.
     */
    public Ui() {}

    /**
     * Prints to user a welcome message when the program is run.
     */
    public static String printWelcome() {
        String hello = "Hello! I'm Chris" + "\n" + "What can I do for you?" + "\n";
        return horizontal + "\n" + hello + horizontal;
    }

    /**
     * Prints to user if the user has entered the terminate command.
     */
    public static String printBye() {
        String bye = "Bye. Hope to see you again soon!" + "\n";
        return horizontal + "\n" + bye + horizontal;
    }

    /**
     * Prints to user if a Task has been marked as complete.
     * @param task the Task that was marked as complete.
     */
    public String printDone(Task task) {
        return horizontal + "Nice! I've marked this task as done:" + "\n" +
                task.toString() + "\n" + horizontal;
    }

    /**
     * Prints to user if a Task has been successfully deleted from the TaskList
     * and the current number of Tasks in it.
     * @param taskList the TaskList associated with the current Duke object.
     * @param task the Task that was deleted from the TaskList.
     */
    public String printDelete(TaskList taskList, Task task) {
        return horizontal + "Noted. I've removed this task:" + "\n" +
                task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list." +
                "\n" + horizontal;
    }

    /**
     * Prints the list of tasks in the TaskList
     * @param taskList the TaskList associated with the current Duke object.
     */
    public String printList(TaskList taskList) {
        StringBuilder tasks = new StringBuilder();
        if (taskList.size() > 0) {
            tasks.append(horizontal + "Here are the tasks in your list:" + "\n");
            for (int i = 1; i <= taskList.size(); i++) {
                Task task = taskList.getTask(i);
                tasks.append(i + "." + task.toString()).append("\n");
            }
        } else {
            tasks.append("You have no tasks mate!");
        }
        tasks.append(horizontal);
        return tasks.toString();
    }

    /**
     * Prints the new Task that has been added.
     * @param taskList the TaskList associated with the current Duke object.
     * @param task the Task that is being added to the TaskList.
     */
    public String printAdd(TaskList taskList, Task task) {
        return horizontal + "Got it. I've added this task:" + "\n" + task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list." + "\n" + horizontal;
    }

    /**
     * Prints to user if a DukeException has been caught, printing an error message.
     * @param e a DukeException containing the error message.
     */
    public String printDukeError(DukeException e) {
        return e.getMessage() + "\n";
    }

    /**
     * Prints to the user an error message when an I0Exception has been caught.
     * @param e an IOException containing an error message.
     */
    public String printIOError(IOException e) {
        return e.getMessage() + "\n";
    }

    /**
     * Prints a statement depending on whether the file has been successfully created.
     * @param b a Boolean whether the file has been created.
     */
    public String printHasCreated(Boolean b) {
        if (b) {
            return "New file created" + "\n";
        } else {
            return "Failed to create file";
        }

    }

    public String printError(String message) {
        return "Oh no there seems to be an error" + message;
    }

    /**
     * Prints to user all the tasks that had been saved in the TaskList.
     * @param taskList the TaskList associated with the current Duke object.
     */
    public static String printSaved(TaskList taskList) {
        StringBuilder tasks = new StringBuilder();
        if (taskList.size() > 0) {
            tasks.append("Here are your saved tasks: \n");
            for (int i = 1; i <= taskList.size(); i++) {
                Task task = taskList.getTask(i);
                tasks.append(i + ". " + task.toString()).append("\n");
            }
        } else {
           tasks.append("You have no tasks mate.");
        }
        return tasks.toString();
    }


    /**
     * Prints to user the tasks that contain a match to the word.
     * @param word the word that is being searched for.
     * @param taskList the TaskList associated with the current Duke object.
     */
    public String find(String word, TaskList taskList) {
        assert(!word.isEmpty());
        assert(taskList.size() > 0);
        TaskList list = new TaskList();
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.description.contains(word)) {
                list.addTask(task);
            }
        }
        StringBuilder tasks = new StringBuilder();
        if (list.size() > 0) {
            tasks.append(horizontal + "Here are the matching tasks in your list: \n");
            for (int i = 1; i <= list.size(); i++) {
                tasks.append(String.format("%s. %s%n", i, list.getTask(i).toString()))
                        .append("\n");
            }
        } else {
            tasks.append("There are no matching tasks.");
        }
        return tasks.toString();
    }
}
