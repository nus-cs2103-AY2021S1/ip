import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Implements methods that prints out messages to console.
 */

public class Ui {
    private final BufferedReader inputReader;

    /**
     * Instantiates Ui object and BufferedReader object to scan user input.
     */
    public Ui() {
        Reader inputStreamReader = new InputStreamReader(System.in);
        this.inputReader = new BufferedReader(inputStreamReader);
    }

    /**
     * Outputs the total number of tasks in ArrayList.
     */
    public void printTaskCount() {
        if (Task.totalTasks > 1) {
            System.out.println("You have a total of " + Task.totalTasks + " tasks in the list.");
        } else {
            System.out.println("You have a total of " + Task.totalTasks + " task in the list.");
        }
        printBorder();
    }

    /**
     * Outputs welcome message.
     */
    public void printWelcomeMessage() {
        String logo = " ______   _____  _____  ___  ____   ________  \n"
                + "|_   _ `.|_   _||_   _||_  ||_  _| |_   __  | \n"
                + "  | | `. \\ | |    | |    | |_/ /     | |_ \\_| \n"
                + "  | |  | | | '    ' |    |  __'.     |  _| _  \n"
                + " _| |_.' /  \\ \\__/ /    _| |  \\ \\_  _| |__/ | \n"
                + "|______.'    `.__.'    |____||____||________| ";
        System.out.println("Hello there! My name is \n" + logo + "\nHow may I assist you today?");
        printBorder();
    }

    /**
     * Outputs exit message after exiting program.
     */
    public void printByeMessage() {
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    /**
     * Outputs done message after task is completed.
     * @param task Task that is added.
     */
    public void doneMessage(Task task) {
        System.out.println("Great job! This task has been marked as done:");
        System.out.println(task);
        printBorder();
    }

    /**
     * Outputs added message after task is added.
     */
    public void addedMessage(Task task) {
        System.out.println("Thank you for your input. The following task has been added to the list:");
        String outputString = task.toString();
        System.out.println(" " + outputString);
    }

    /**
     * Outputs remove message after task is removed.
     */
    public void removeMessage(Task task) {
        System.out.println("The following task has been successfully removed:");
        System.out.println(task);
    }

    /**
     * Parses input lines.
     * @return String that is desired.
     */
    public String parseInput() {
        try {
            return inputReader.readLine();
        } catch (IOException error) {
            System.out.println("Error reading user input.");
            return null;
        }
    }

    /**
     * Exits program.
     */
    public void exitProgram() {
        try {
            inputReader.close();
        } catch (IOException error) {
            System.out.println("Error closing user input stream.");
        }
    }

    /**
     * Lists tasks in ArrayList.
     * @param arrayOfTasks Our main ArrayList.
     */
    public void listTasks(TaskList arrayOfTasks) {
        String output = arrayOfTasks.toString();
        System.out.println(output);
        printBorder();
    }

    /**
     * Prints border for easier reading of output.
     */
    public void printBorder() {
        System.out.print("---------------------------\n");
    }

    /**
     * Prints message to show matching tasks.
     */
    public void matchingMessage(TaskList arrayOfTasks) {
        System.out.println("These are the matching tasks in your list:");
        System.out.println(arrayOfTasks.toString());
        printBorder();
    }

    /**
     * Prints message to say that there are no matching tasks found.
     */
    public void noMatchMessage() {
        System.out.println("There are no matching tasks in your list.");
        printBorder();
    }
}
