package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Handles the interaction with the user.
 */
public class Ui {

    /**
     * Uses Scanner to take in user input
     *
     * @return user input as a String
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Greeting messages when bot starts up
     */
    public void start() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Goodbye messages upon bot exits
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Messages to be printed when a task is added
     *
     * @param taskList current list of tasks
     */
    public void printAddTask(TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + taskList.getTask(taskList.getList().size() - 1));
        System.out.println("Now you have " + taskList.getList().size() + " tasks in the list." );
    }

    /**
     * Messages to be printed when a task is deleted
     *
     * @param taskList current list of tasks
     * @param task tasks that is deleted
     */
    public void printDeleteTask(TaskList taskList, Task task) {
        System.out.println("Noted. I've deleted this task:\n" + task);
        System.out.println("Now you have " + (taskList.getList().size()) + " tasks in the list." );
    }

    /**
     * Messages to be printed when a task has been completed
     * @param task task that has been completed
     */
    public void printDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints the current set of tasks in the list
     *
     * @param list current list of tasks
     */
    public void printList(TaskList list) {
        int i = 1;
        if (list.getList().isEmpty()) {
            System.out.println("List is Empty");
        }
        for(Task t : list.getList()) {
            System.out.println(i + "." + t);
            i++;
        }
    }

<<<<<<< HEAD
    /**
     * Prints the error message
     * @param error error message
     */
=======
    public void printFind() {
        System.out.println("Here are the matching tasks in your list:");
    }
>>>>>>> branch-Level-9
    public void showError(String error) {
        System.out.println(error);
    }
}
