package dude.ui;

import java.util.Scanner;

/**
 * The class the handles all the on screen interaction with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    private Scanner scanner;
    private String message;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Greeting to the user.
     */
    public void greet() {
        StringBuilder str = new StringBuilder();
        str.append("Yo I'm Dood!!\nAnything I can do for you?\n").append("The commands available are:\n")
                .append("list     | Shows the list of tasks on the bot.\n")
                .append("bye      | Exits the program\n")
                .append("done     | Marks the Task as done. Format is 'done {task index}.\n")
                .append("todo     | Creates a ToDo task. Format is 'Todo {description}.\n")
                .append("event    | Creates an Event task. Format is 'event {description} /at {date in YYYY-MM-DD}.\n")
                .append("deadline | Creates a DeadLine task. Format is 'deadline {description} /by {date in YY-MM-DD}.")
                .append("\ndelete   | Deletes a Task. Format is ' delete {task index}");
        System.out.println(template(str.toString()));
    }

    public void sendOff() {
        message = "See you again!!";
    }

    public String template(String reply) {
        return LINE + reply + "\n" + LINE;
    }

    public void showFileNotFound() {
        System.out.println(template("No existing user data file found. Welcome to the bot club:)"));
    }

    public void showLoadingError() {
        System.out.println(template("Your file has been corrupted :( Unfortunately the data cannot be used"));
    }

    public void showError(String message) {
        System.out.println(template(message));
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showMessage() {
        System.out.println(template(message));
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
