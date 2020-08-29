package duke.ui;

import java.util.Scanner;
import duke.exception.DukeException;
import duke.task.*;

/**
 * The Ui class handles interaction with the user, and is responsible for
 * creating display messages.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
      this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Generates a line of # to separate text.
     */
    public void showLine() {
        System.out.println("#############################################################");
    }

    /**
     * Displays the error message of a DukeException to the user.
     * @param e DukeException provided.
     */
    public void showError(DukeException e) {
        System.out.println(e.toString());
    }

    /**
     * Shows error message if problem exists at creation of a new Duke object.
     */
    public void showLoadingError() {
        System.out.println(
                "Error at load.");
    }

    /**
     * Greeting message that shows when Duke successfully starts up.
     */
    public void greetingMessage() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo +
        "____________________________________________________________\n" +
        " Hello! I'm Duke\n" +
        " What can I do for you?\n" +
                "____________________________________________________________");
    }

    /**
     * Display message for when a new Todo, Deadline or Event is added.
     * @param t Given Task that is added.
     * @param tasks TaskList kept by Duke.
     */
    public void addTaskMessage(Task t, TaskList tasks) {
        String message =
                "Got it. I've added this task:\n" +
                "  " + t.toString() + "\n" +
                "Now you have " + tasks.getNumOfTasks() + " tasks in the list.";
        System.out.println(message);
    }

    /**
     * Display message when a Task is marked as done.
     * @param t Given Task that is completed.
     */
    public void doneTaskMessage(Task t) {
        String message =
        "Nice! I've marked this task as done:\n" +
        "  " + t.toString();
        System.out.println(message);
    }

    /**
     * Display message when a Task is deleted from the TaskList.
     * @param t Given Task that has been removed.
     * @param tasks TaskList kept by Duke.
     */
    public void deleteTaskMessage(Task t, TaskList tasks) {
        String message =
                "Noted. I've removed this task:\n" +
                        "  " + t.toString() + "\n" +
                        "Now you have " + tasks.getNumOfTasks() + " tasks in the list.";
        System.out.println(message);
    }

    /**
     * Prints out a list of all tasks in the TaskList.
     * @param tasks TaskList kept by Duke.
     */
    public void listTasksMessage(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.getNumOfTasks(); i++) {
            Task t = tasks.retrieve(i);
            System.out.format("%d.%s\n", i, t.toString());
        }
    }

    /**
     * Prints out a list of all tasks that whose descriptions contain the given keyWords.
     * @param tasks TaskList kept by Duke.
     * @param keyWords Key words entered by user.
     */
    public void findTasksMessage(TaskList tasks, String keyWords) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.getNumOfTasks(); i++) {
            Task t = tasks.retrieve(i);
            if (t.contains(keyWords)) {
                System.out.format("%d.%s\n", i, t.toString());
            }
        }
    }

    /**
     * Display message when Duke is told to close.
     */
    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
