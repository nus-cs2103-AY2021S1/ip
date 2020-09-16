package duke.ui;

import java.util.Scanner;

/**
 * Handles console input and output.
 */
public class TextUi {

    private static final String INTRODUCTION = "Hi, I'm your Professor, Martin. I keep track of\n"
            + "your tasks and load them if you have any saved.\n"
            + "What can I do for you? You can ask me to do these:\n"
            + "list: List the current tasks in your list.\n"
            + "bye: Saves any tasks in the list and quits the program.\n"
            + "todo: Add a To-Do task.\n"
            + "event: Add an event task.\n"
            + "deadline: Add a deadline task.\n"
            + "done: Mark task as done based on the task's number.\n"
            + "delete: Deletes task based on the task's number.\n"
            + "find: Find a task which matches your description.";;
    private Scanner reader;

    /**
     * Initialise Ui with scanner.
     */
    public TextUi() {
        reader = new Scanner(System.in);
    }

    /**
     * Reads input from user.
     * @return Console input string from user.
     */
    public String readCommand() {
        System.out.println("Enter input:");
        return reader.hasNextLine() ? reader.nextLine() : "";
    }

    /**
     * Prints the introduction message onto console.
     */
    public void showIntroduction() {
        System.out.println(INTRODUCTION);
    }

    /**
     * Prints a dashed line to indicate a new section of the program.
     */
    public void showLine() {
        System.out.println("------------------------------------------------");
    }

    /**
     * Displays messages onto console.
     * @param message Message to printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the Scanner to save resources.
     */
    public void closeUi() {
        reader.close();
    }

    /**
     * Prints an exception's message onto console.
     * @param e Exception to be printed onto console.
     */
    public void showException(Exception e) {
        System.out.println(e);
    }

}
