import java.io.IOException;
import java.util.Scanner;

/**
 * Ui class that deals with interactions with the user.
 */
public class Ui {
    public Scanner sc;

    public static String horizontal = "________________________________" + "\n";

    /**
     * Constructor that creates a Ui object, with a Scanner that reads user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints to user a welcome message when the program is run.
     */
    public void welcome() {
        String hello = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(horizontal + hello + horizontal);
    }

    /**
     * Prints to user if the user has entered the terminate command.
     */
    public void printBye() {
        String bye = "Bye. Hope to see you again soon!" + "\n";
        System.out.println(horizontal + bye + horizontal);
    }

    /**
     * Prints to user if a Task has been marked as complete.
     * @param task the Task that was marked as complete.
     */
    public void printDone(Task task) {
        System.out.println(horizontal + "Nice! I've marked this task as done:" + "\n" +
                task.toString() + "\n" + horizontal);
    }

    /**
     * Prints to user if a Task has been successfully deleted from the TaskList
     * and the current number of Tasks in it.
     * @param taskList the TaskList associated with the current Duke object.
     * @param task the Task that was deleted from the TaskList.
     */
    public void printDelete(TaskList taskList, Task task) {
        System.out.println(horizontal + "Noted. I've removed this task:" + "\n" +
                task.toString() + "\n" + "Now you have " + taskList.size() + " tasks in the list." +
                "\n" + horizontal);
    }

    /**
     * Prints the list of tasks in the TaskList
     * @param taskList the TaskList associated with the current Duke object.
     */
    public void printList(TaskList taskList) {
        System.out.println(horizontal + "Here are the tasks in your list:" + "\n");
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            System.out.println(i + "." + task.toString());
        }
        System.out.println(horizontal);
    }

    /**
     * Prints the new Task that has been added.
     * @param taskList the TaskList associated with the current Duke object.
     * @param task the Task that is being added to the TaskList.
     */
    public void printAdd(TaskList taskList, Task task) {
        System.out.println(horizontal + "Got it. I've added this task:" + "\n" + task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list." + "\n" + horizontal);
    }

    /**
     * Prints to user if a DukeException has been caught, printing an error message.
     * @param e a DukeException containing the error message.
     */
    public void printDukeError(DukeException e) {
        System.out.println(e.getMessage() + "\n");
    }

    /**
     * Prints to the user an error message when an I0Exception has been caught.
     * @param e an IOException containing an error message.
     */
    public void printIOError(IOException e) {
        System.out.println(e.getMessage() + "\n");
    }

    /**
     * Prints a statement depending on whether the file has been successfully created.
     * @param b a Boolean whether the file has been created.
     */
    public void printHasCreated(Boolean b) {
        if (b) {
            System.out.println("New file created" + "\n");
        } else {
            System.out.println("Failed to create file");
        }

    }

    /**
     * Prints to user all the tasks that had been saved in the TaskList.
     * @param taskList the TaskList associated with the current Duke object.
     */
    public void printSaved(TaskList taskList) {
        if (taskList.size() > 0) {
            System.out.println("Here are your saved tasks: \n");
            for (int i = 1; i <= taskList.size(); i++) {
                Task task = taskList.getTask(i);
                System.out.println(i + ". " + task.toString());
            }
        } else {
            System.out.println("You have no tasks.");
        }
        System.out.println(horizontal);
    }


    /**
     * Prints to user the tasks that contain a match to the word.
     * @param word the word that is being searched for.
     * @param taskList the TaskList associated with the current Duke object.
     */
    public void find(String word, TaskList taskList) {
        TaskList list = new TaskList();
        for (int i = 1; i <= taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.description.contains(word)) {
                list.addTask(task);
            }
        }
        if (list.size() > 0) {
            System.out.println(horizontal + "Here are the matching tasks in your list: \n");
            for (int i = 1; i <= list.size(); i++) {
                System.out.printf("%s. %s%n", i, list.getTask(i).toString());
            }
        } else {
            System.out.println("There are no matching tasks.");
        }
        System.out.println(horizontal);
    }
}
