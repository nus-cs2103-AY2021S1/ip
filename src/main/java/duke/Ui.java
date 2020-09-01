package duke;

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
    protected final TaskListHandler handler;
    protected final Storage storage;

    /**
     * Initializes the Ui with the task list and storage.
     *
     * @param handler Contains the task list and its operations.
     * @param storage Contains all operations related to loading and saving of tasks to disk.
     */
    public Ui(TaskListHandler handler, Storage storage) {
        this.handler = handler;
        this.storage = storage;
    }

    /**
     * Stops the user interface and terminates the program.
     */
    public static void stopRunning() {
        isRunning = false;
    }

    /**
     * Runs the user interface which scans for input, parsing it and executes valid commands.
     * Terminates when empty line is detected, when input file is used.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            // Listens for input
            try {
                String input = scanner.nextLine();
                Command c = Parser.parse(input, handler);
                c.execute(handler, storage);
                System.out.println();
            } catch (NoSuchElementException e1) {
                // Encounter end of file, terminate
                isRunning = false;
            } catch (DukeException e) {
                e.printStackTrace(System.out);
                DukeException.tryAgain();
            }
        }
    }

    /**
     * Draws the top border of the chatbot's response.
     */
    public static void drawTopBorder() {
        System.out.println(" ______________________________________________________________ ");
        System.out.println("*                                                              *");
    }

    /**
     * Draws the bottom border of the chatbot's response.
     */
    public static void drawBottomBorder() {
        System.out.println();
        System.out.println("*______________________________________________________________*");

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
     * @param listSize Size of task list.
     */
    public static void printSuccess(String operation, Task currentTask, int listSize) {
        // Prints success message and list size after task added/deleted
        drawTopBorder();
        indent(1);
        if (operation.equals("add")) {
            System.out.print("Yes! I have successfully added:\n");
        } else if (operation.equals("delete")) {
            System.out.print("Alright! I've removed this task:\n");
        } else {
            System.out.println("Good job! You completed:");
            indent(2);
            System.out.println(currentTask);
            drawBottomBorder();
            return;
        }
        indent(2);
        System.out.println(currentTask);
        indent(1);
        System.out.println("You have " + listSize + " task(s) in the list.");
        drawBottomBorder();
    }

    /**
     * Initial greeting message in unicode.
     */
    public static void greet() {
        drawTopBorder();
        String logo = "\n"
            + "     ___              ___                  \n"
            + "    (   )            (   )                 \n"
            + "  .-.| |   ___  ___   | |   ___     .--.   \n"
            + " /   \\ |  (   )(   )  | |  (   )   /    \\  \n"
            + "|  .-. |   | |  | |   | |  ' /    |  .-. ; \n"
            + "| |  | |   | |  | |   | |,' /     |  | | | \n"
            + "| |  | |   | |  | |   | .  '.     |  |/  | \n"
            + "| |  | |   | |  | |   | | `. \\    |  ' _.' \n"
            + "| '  | |   | |  ; '   | |   \\ \\   |  .'.-. \n"
            + "' `-'  /   ' `-'  /   | |    \\ .  '  `-' / \n"
            + " `.__,'     '.__.'   (___ ) (___)  `.__.'  \n"
            + "                                           \n"
            + "                                           \n";
        System.out.println(logo);
        String greeting = "Hey! I'm Duke the chatbot!";
        String doForYou = "What can I do for you?";
        indent(1);
        System.out.println(greeting);
        indent(1);
        System.out.println(doForYou);
        drawBottomBorder();
    }

}
