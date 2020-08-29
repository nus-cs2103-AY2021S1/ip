package duke.core;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui class interacts with the user by accpeting
 * the command entered by the user and showing
 * the result of the execution of the command.
 */
public class Ui {
    public static final String INDENTATION = "    ";
    public static final String DIVIDER = "____________________________________________________________";
    public static final String GREETING = "Hello! I am Smith\n" + "What can I do for you?";
    public static final String EXITMESSAGE = "Bye. Hope to see you again soon!";
    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public final Scanner scanner;

    PrintStream out;

    /**
     * Creates a user interface component.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);

        try {
            out = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException exception) {
            System.out.println(makeBlock(exception.getMessage()));
        }
    }

    /**
     * Adds two lines for the information to make a presentation block.
     *
     * @param string The information.
     * @return The decorated version of the information.
     */
    public String makeBlock(String string) {
        String[] strings = string.split("\n");
        String result = INDENTATION + DIVIDER + "\n";
        for(int i = 0; i < strings.length; i = i + 1) {
            result = result + INDENTATION + strings[i] + "\n";
        }
        result = result + INDENTATION + DIVIDER + "\n";
        return  result;
    }

    /**
     * Reads the entered command.
     *
     * @return The entered command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the greeting information.
     */
    public void showGreeting() {
        //System.out.println("Hello from\n" + logo);
        out.println(makeBlock(logo + "\n" + GREETING));
    }

    /**
     * Handles the possible problems by showing the corresponding information to the user.
     *
     * @param exception The exception corresponding to the problem.
     */
    public void handle(Exception exception) {
        out.println(makeBlock(exception.getMessage()));
    }

    /**
     * Shows the result of the executing the add command.
     *
     * @param task The task to be added.
     * @param size The number of tasks after the execution.
     */
    public void showAdd(Task task, int size) {
        String result = "Got it. I have added this task:\n  " + task.toString() + "\nNow you have " + size + " tasks in the list.";
        //System.out.println(makeBlock(result));
        out.println(makeBlock(result));
    }

    /**
     * Shows the result of the executing the list command.
     *
     * @param taskList The task list.
     */
    public void showList(TaskList taskList) {
        out.println(makeBlock(taskList.toString()));
    }

    /**
     * Shows the result of the executing the complete command.
     *
     * @param task The task to be marked as completed.
     * @param count The count of the task to be completed.
     */
    public void showDone(Task task, int count) {
        out.println(makeBlock("Nice! I have marked this task as done:\n" + String.valueOf(count) + "." + task.toString()));
    }

    /**
     * Shows the result of the executing of the delete command.
     *
     * @param task The task to be deleted.
     * @param count The count of the task.
     * @param size The number of tasks in the task list.
     */
    public void showDelete(Task task, int count, int size) {
        out.println(makeBlock("Noted. I have removed this task:\n" +
                String.valueOf(count) +
                "." + task.toString() +
                "\nNow you have " + size + " tasks in the list."));
    }

    /**
     * Shows the result of the executing the exit command.
     */
    public void showExit() {
        out.println(makeBlock(EXITMESSAGE));
    }
}
