package duke;

import java.util.List;
import java.util.Scanner;

import duke.resource.TaskList;
import duke.task.Task;
import duke.util.DukeException;

/**
 * Ui class that handles the user interaction component of the bot.
 */

public class Ui {

    private final Scanner sc;

    private static final String LINE =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    /**
     * Constructor that creates a Ui object, with a Scanner that reads user input.
     */

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints to user if the given save file has loaded successfully and the tasks found
     * in the save file.
     * @param tasks a TaskList containing the tasks in the save file
     */

    public void loaded(TaskList tasks) {
        System.out.println(
                "    A save file has been found and loaded!\n" +
                "    Your current tasks are: ");
        int i = 0;
        if (tasks.size() == 0) {
            System.out.println("    ... empty! Good work!");
        } else {
            while (tasks.size() > i) {
                System.out.println("        " + ++i + ". " + tasks.getTask(i).toString());
            }
        }
        System.out.println(LINE);
    }

    /**
     * Prints to user if a Task has successfully been added to the TaskList and
     * the current number of Tasks in it.
     * @param tasks the TaskList associated with the current Duke object.
     * @param task the Task that was added to the TaskList.
     */

    public void printAdd(TaskList tasks, Task task) {
        System.out.println(
                "    Got it. I've added this task:\n        " +
                task.toString() +
                "\n    You now have " + tasks.size() +
                (tasks.size() == 1 ? " task" : " tasks") +
                " in your list.\n" +
                LINE);
    }

    /**
     * Prints to user if the user has entered the terminate command.
     */

    public void printBye() {
        System.out.println(
                "    Bye. Hope to see you again soon!\n" +
                LINE);
    }

    /**
     * Prints to user if a Task has been marked as complete.
     * @param task the Task that was marked as complete.
     */

    public void printDone(Task task) {
        System.out.println(
                "    Nice! I've marked this task as done:\n        " +
                task.toString() + "\n" +
                LINE);
    }

    /**
     * Prints to user if a Task has successfully been deleted from the TaskList and
     * the current number of Tasks in it.
     * @param tasks the TaskList associated with the current Duke object.
     * @param task the Task that was deleted from the TaskList.
     */

    public void printDelete(TaskList tasks, Task task) {
        System.out.println(
                "    Noted. I've removed this task:\n        " +
                task.toString() +
                "\n    You now have " + tasks.size() +
                (tasks.size() == 1 ? " task" : " tasks") +
                " in your list.\n" +
                LINE);
    }

    /**
     * Prints to user if a DukeException has been caught, printing the corresponding
     * error message.
     * @param e a DukeException containing the error message
     */

    public void printError(DukeException e) {
        System.out.println(
                e.getMessage() + "\n" +
                LINE);
    }

    /**
     * Prints to user a list of matching Tasks given a keyword to search for
     * @param list a list of matching Tasks
     */

    public void printFind(List<Task> list) {
        int i = 0;
        System.out.println("    Here are the matching tasks in your list: ");
        while (list.size() > i) {
            System.out.println("        " + ++i + ". " + list.get(i - 1).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Prints to user the current Tasks found in the TaskList.
     * @param list the TaskList associated with the current Duke object
     */

    public void printList(TaskList list) {
        int i = 0;
        System.out.println("    Here are the tasks in your list: ");
        while (list.size() > i) {
            System.out.println("        " + ++i + ". " + list.getTask(i).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Reads and returns the next line of user input.
     * @return the next line of user input
     */

    public String read() {
        return sc.nextLine();
    }

    /**
     * Prints a starting message to indicate that Duke is ready to take in user input.
     */

    public void start() {
        System.out.println(
                "    Awaiting input...\n" +
                LINE);
    }

    /**
     * Prints a welcome message for the user.
     */

    public void welcome() {
        String logo
                = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE + "\n" + logo + "\n    Hello! I'm Duke!\n");
    }

}
