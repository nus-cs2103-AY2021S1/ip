package duke;

import java.util.Scanner;

/**
 * Represents the Duke User Interface which deals with interactions with the user
 */
public class Ui {
    public Ui() { }

    /**
     * Processes DukeException errors and displays them to the user.
     *
     * @param e The DukeException thrown
     */
    public void showLoadingError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void greeting() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public String goodbye() {
        System.out.println("Bye! Hope to see you again soon!");
        return "Bye";
    }

    /**
     * Displays the user's current task list.
     *
     * @param tasks The current TaskList of the User.
     */
    public void list(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTasks().get(i).recordString());
        }
    }

    /**
     * Notifies the user that the specified task has been marked done.
     *
     * @param t The specified task that was marked done.
     */
    public void done(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.recordString());
    }

    /**
     * Notifies the user that the specified task has been deleted.
     * Notifies the user how many tasks are left in the current TaskList.
     *
     * @param t The specified task that was deleted.
     * @param size The number of tasks in the TaskList.
     */
    public void delete(Task t, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.recordString());
        System.out.println("Now, you have " +  size + " tasks in the list");
    }

    /**
     * Notifies the user that the specified task has been added.
     * Notifies the user how many tasks are in the updated TaskList.
     *
     * @param t The specified task that was added.
     * @param size The number of tasks in the TaskList.
     */
    public void add(Task t, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.recordString());
        System.out.println("Now, you have " + size + " tasks in the list");
    }

    /**
     * Initializes the Duke UI to receive user inputs.
     *
     * @param tasks The specified task that was marked done.
     */
    public void initializeDukeUI(TaskList tasks) {
        greeting();
        try {
            Scanner userInput = new Scanner(System.in);
            while (userInput.hasNext()) {
                String input = userInput.nextLine();
                String s = Parser.parse(input, tasks);
                if (s.equals("Bye")) {   break;
                }
            }
        } catch (DukeException e) {
            showLoadingError(e);
        }
    }
}
