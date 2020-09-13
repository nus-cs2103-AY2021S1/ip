package duke;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.command.Command;
import duke.task.Task;

/**
 * Represents all user interaction with Duke.
 */
public class Ui {
    /** Class-wide variable to know whether to continue running user interface. */
    protected static boolean isRunning = true;
    protected static String logo;
    protected final TaskListHandler taskListHandler;
    protected final Storage storage;


    /**
     * Initializes the Ui with the task list and storage.
     *
     * @param taskListHandler Contains the task list and its operations.
     * @param storage Contains all operations related to loading and saving of tasks to disk.
     */
    public Ui(TaskListHandler taskListHandler, Storage storage) {
        this.taskListHandler = taskListHandler;
        this.storage = storage;
    }
    public static void exitDuke() {
        isRunning = false;
    }

    /**
     * Runs the user interface which scans for input, parsing it and executes valid commands.
     * Terminates when empty line is detected, when input file is used.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            try {
                String input = scanner.nextLine();
                Command c = Parser.parse(input, taskListHandler);
                c.execute(taskListHandler, storage, input);
                System.out.println();
            } catch (NoSuchElementException e1) {
                // Encounter end of file, terminate
                isRunning = false;
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                DukeException.tryAgain();
            }
        }
    }

    /**
     * Provides indentation for formatting.
     *
     * @param times The number of 4 spaces to be printed.
     */
    public static void indent(int times) {
        for (int i = 0; i < times; i++) {
            System.out.print("    ");
        }
    }

    /**
     * Prints success message and list size after task added/deleted.
     *
     * @param operation Type of command.
     * @param currentTask Task that was modified.
     * @param taskList Task list.
     */
    public static void printSuccess(String operation, Task currentTask, ArrayList<Task> taskList) {
        // Prints success message and list size after task added/deleted
        switch (operation) {
        case "add":
            System.out.print("Yes! I have successfully added:\n");
            break;
        case "delete":
            System.out.print("Alright! I've removed this task:\n");
            break;
        case "done":
            System.out.println("Good job! You completed:");
            break;
        case "find":
            System.out.println("I have found the matching tasks in your list: ");
            for (Task t: taskList) {
                indent(1);
                System.out.println(t);
            }
            break;
        default:
            assert false : "Error printing success message of " + operation;
            return;
        }
        indent(1);
        System.out.println(currentTask);
        System.out.println();
    }

    /**
     * Prints the greeting message.
     */
    public static void greet() {
        System.out.println(getGreeting());
    }

    /**
     * Retrieves the greeting in string format for GUI.
     *
     * @return Greeting.
     */
    public static String getGreeting() {
        setLogo();
        String greeting = "Hey! I'm Duke the chatbot!";
        String doForYou = "What can I do for you?";
        return logo + "\n"
            + greeting + "\n"
            + doForYou + "\n";
    }

    /**
     * Initializes the duke logo.
     */
    public static void setLogo() {
        logo = "              D U K E";
    }
}
