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
    public static final String INDENTATION = "";
    public static final String DIVIDER = "____________________________";
    public static final String GREETING = "Hello! I am Smith\n" + "What can I do for you?";
    public static final String EXITMESSAGE = "Bye. Hope to see you again soon!";
    public static final String LOADMESSAGE = "Loading the local record";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public final Scanner scanner;

    private PrintStream out;

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

        for (int i = 0; i < strings.length; i = i + 1) {
            result = result + INDENTATION + strings[i] + "\n";
        }

        result = result + INDENTATION + DIVIDER + "\n";
        return result;
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
     *
     * @return The corresponding message.
     */
    public String showGreeting() {
        //System.out.println("Hello from\n" + logo);
        //out.println(makeBlock(logo + "\n" + GREETING));
        return makeBlock(LOGO + "\n" + GREETING + "\n"
                + "To see the list of available commands, type help");
    }

    /**
     * Handles the possible problems by showing the corresponding information to the user.
     *
     * @param exception The exception corresponding to the problem.
     * @return The corresponding message.
     */
    public String handleException(Exception exception) {
        //out.println(makeBlock(exception.getMessage()));
        return makeBlock(exception.getMessage());
    }

    /**
     * Shows the result of the executing the add command.
     *
     * @param task The task to be added.
     * @param size The number of tasks after the execution.
     * @return The corresponding message.
     */
    public String getAddMessage(Task task, int size) {
        String result = "Got it. I have added this task:\n  "
                + task.toString() + "\nNow you have "
                + size + " tasks in the list.";
        //System.out.println(makeBlock(result));
        //out.println(makeBlock(result));
        return makeBlock(result);
    }

    /**
     * Shows the help message with the list of available commands.
     *
     * @return The help message with the list of available commands.
     */
    public String getHelpMessage() {
        String result = "Here is the list of commands available\n"
                + "Note that the name in [] indicates that the argument is optional\n"
                + "1. list [yyyy-MM-dd]\n"
                + "If the date argument is empty, list out all the tasks\n"
                + "If the date argument is not empty, list out the tasks at the specified time\n"
                + "example: list 2014-12-07\n"
                + "2. todo description\n"
                + "Add a task to do\n"
                + "example: todo something\n"
                + "3. deadline description /by yyyy-MM-dd\n"
                + "Add a deadline by sometime\n"
                + "example: deadline something /by 2014-12-07\n"
                + "4. event description /at yyyy-MM-dd\n"
                + "Add an event at sometime\n"
                + "example: event something /at 2014-12-07\n"
                + "5. periodTask description /start yyyy-MM-dd /end yyyy-MM-dd\n"
                + "Add a task that needs to be completed from the start time to the end time\n"
                + "example: periodTask something /start 2014-12-07 /end 2014-12-08\n"
                + "6. find key\n"
                + "Search for the tasks with the description that contains the key and list the tasks\n"
                + "example: find description\n"
                + "7. help\n"
                + "List the available commands\n"
                + "8. bye\n"
                + "Exit\n";
        return result;
    }

    /**
     * Shows the result of the executing the list command.
     *
     * @param taskList The task list.
     * @return The corresponding message.
     */
    public String getTaskListMessage(TaskList taskList) {
        //out.println(makeBlock(taskList.toString()));
        return makeBlock(taskList.toString());
    }

    /**
     * Shows the result of the executing the complete command.
     *
     * @param task The task to be marked as completed.
     * @param count The count of the task to be completed.
     * @return The corresponding message.
     */
    public String getDoneMessage(Task task, int count) {
        return makeBlock("Nice! I have marked this task as done:\n"
                + String.valueOf(count)
                + "." + task.toString());
    }

    /**
     * Shows the result of the executing of the delete command.
     *
     * @param task The task to be deleted.
     * @param count The count of the task.
     * @param size The number of tasks in the task list.
     * @return The corresponding message.
     */
    public String getDeleteMessage(Task task, int count, int size) {
        /*
        out.println(makeBlock("Noted. I have removed this task:\n" +
                String.valueOf(count) +
                "." + task.toString() +
                "\nNow you have " + size + " tasks in the list."));
        */
        return makeBlock(
                "Noted. I have removed this task:\n"
                        + String.valueOf(count) + "."
                        + task.toString() + "\nNow you have "
                        + size + " tasks in the list.");
    }

    public String showLoad() {
        return makeBlock(LOADMESSAGE);
    }

    /**
     * Shows the result of the executing the exit command.
     *
     * @return The corresponding message.
     */
    public String showExit() {
        //out.println(makeBlock(EXITMESSAGE));
        return makeBlock(EXITMESSAGE);
    }
}
