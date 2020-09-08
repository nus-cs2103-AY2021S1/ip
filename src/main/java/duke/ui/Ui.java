package duke.ui;

import java.util.List;
import java.util.Scanner;

import duke.resource.TaskList;
import duke.task.Task;
import duke.util.DukeException;

/**
 * Ui class that handles the user interaction component of the bot.
 */

public class Ui {

    private static final String LINE =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private final Scanner sc;


    /**
     * Constructor that creates a Ui object, with a Scanner that reads user input.
     */

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints to user if the given save file has loaded successfully and the tasks found
     * in the save file.
     *
     * @param tasks a TaskList containing the tasks in the save file
     */

    public String printLoaded(TaskList tasks) {
        StringBuilder ret = new StringBuilder("A save file has been found and loaded!\n"
                + "Your current tasks are: \n");
        int i = 0;
        if (tasks.size() == 0) {
            ret.append("... empty! Good work!\n");
        } else {
            while (tasks.size() > i) {
                ret.append(String.format("%d. %s\n", ++i, tasks.getTask(i).toString()));
            }
        }
        return ret.toString();
    }

    /**
     * Prints to user if a Task has successfully been added to the TaskList and
     * the current number of Tasks in it.
     *
     * @param tasks the TaskList associated with the current Duke object.
     * @param task the Task that was added to the TaskList.
     */

    public void printAdd(TaskList tasks, Task task) {
        System.out.println(
                "    Got it. I've added this task:\n        "
                        + task.toString()
                        + "\n    You now have " + tasks.size()
                        + (tasks.size() == 1 ? " task" : " tasks")
                        + " in your list.\n"
                        + LINE);
    }

    public String showAdd(TaskList tasks, Task task) {
        return String.format("Noted. I've added this task:\n%s\n"
                + "You now have %d %s in your list.", task.toString(),
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Prints to user if the user has entered the terminate command.
     */

    public void printBye() {
        System.out.println(
                "    Bye. Hope to see you again soon!\n"
                        + LINE);
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints to user if a Task has been marked as complete.
     *
     * @param task the Task that was marked as complete.
     */

    public void printDone(Task task) {
        System.out.println(
                "    Nice! I've marked this task as done:\n        "
                        + task.toString() + "\n"
                        + LINE);
    }

    public String showDone(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s\n",
                task.toString());
    }

    /**
     * Prints to user if a Task has successfully been deleted from the TaskList and
     * the current number of Tasks in it.
     *
     * @param tasks the TaskList associated with the current Duke object.
     * @param task the Task that was deleted from the TaskList.
     */

    public void printDelete(TaskList tasks, Task task) {
        System.out.println(
                "    Noted. I've removed this task:\n        "
                        + task.toString()
                        + "\n    You now have " + tasks.size()
                        + (tasks.size() == 1 ? " task" : " tasks")
                        + " in your list.\n"
                        + LINE);
    }

    public String showDelete(TaskList tasks, Task task) {
        return String.format("Noted. I've removed this task:\n%s\n"
                + "You now have %d %s in your list.", task.toString(),
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Prints to user if a DukeException has been caught, printing the corresponding
     * error message.
     *
     * @param e a DukeException containing the error message
     */

    public void printError(DukeException e) {
        System.out.println(
                e.getMessage() + "\n" + LINE);
    }

    public String showError(DukeException e) {
        return String.format("%s\n", e.getMessage());
    }

    /**
     * Prints to user a list of matching Tasks given a keyword to search for
     *
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

    public String showFind(List<Task> list) {
        int i = 0;
        StringBuilder ret = new StringBuilder("Here are the matching tasks in your list:\n");
        while (list.size() > i) {
            ret.append(String.format("%d. %s\n", ++i, list.get(i - 1).toString()));
        }
        return ret.toString();
    }

    /**
     * Prints to user the current Tasks found in the TaskList.
     *
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

    public String showList(TaskList list) {
        int i = 0;
        StringBuilder ret = new StringBuilder("Here are the tasks in your list:\n");
        while (list.size() > i) {
            ret.append(String.format("%d. %s\n", ++i, list.getTask(i).toString()));
        }
        return ret.toString();
    }

    /**
     * Reads and returns the next line of user input.
     *
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
                "    Awaiting input...\n" + LINE);
    }

    /**
     * Prints a welcome message for the user.
     */

    public void printWelcome() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE + "\n" + logo + "\n    Hello! I'm Duke!\n");
    }

    public String welcome() {
        return "Hello! I'm Duke!\n";
    }

}
