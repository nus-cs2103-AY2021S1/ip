package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Encapsulates a Ui class that helps to print output messages.
 */
public class Ui {
    private static final int PADDING_LEFT_LENGTH = 5;
    private static final int DIVIDER_LENGTH = 60;
    private static String PADDING_LEFT = "";
    private static String DIVIDER = "";

    /**
     * Generates left padding of the output message.
     */
    public void generateLeftPadding() {
        if (PADDING_LEFT.equals("")) {
            for (int i = 0; i < PADDING_LEFT_LENGTH; i++) {
                PADDING_LEFT += " ";
            }
        }
        System.out.print(PADDING_LEFT);
    }

    /**
     * Generates divider of the output message.
     */
    public void generateDivider() {
        if (DIVIDER.equals("")) {
            for (int j = 0; j < DIVIDER_LENGTH; j++) {
                DIVIDER += "_";
            }
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints the welcome message upon starting the program.
     */
    public void printWelcomeMessage() {
        String logo = "    _____                 ________  .__\n"
                + "   /     \\_______  ______ \\______ \\ |__| ____   ____\n"
                + "  /  \\ /  \\_  __ \\/  ___/  |    |  \\|  |/    \\ /  _ \\ \n"
                + " /    Y    \\  | \\/\\___ \\   |    |   \\  |   |  (  <_> ) \n"
                + " \\____|__  /__|  /____  > /_______  /__|___|  /\\____/ \n"
                + "         \\/           \\/          \\/        \\/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome!");
        System.out.println("What can Mrs Dino do for you?");
    }

    /**
     * Prints the exit message.
     */
    public void printBye() {
        generateDivider();
        generateLeftPadding();
        System.out.println("Bye. Hope to see you again soon!");
        generateDivider();
    }

    /**
     * Prints the message after a task is marked as done.
     *
     * @param message String representation of the task marked as done.
     */
    public void printDone(String message) {
        generateDivider();
        System.out.println("Nice! I've marked this task as done:");
        generateLeftPadding();
        System.out.println(message);
        generateDivider();
    }

    /**
     * Prints the message after a task is added
     *
     * @param message String representation of the task added.
     * @param size New size of the task list after adding the task.
     */
    public void printTaskAdded(String message, int size) {
        generateDivider();
        System.out.println("Got it. I've added this task:");
        generateLeftPadding();
        System.out.println(message);
        System.out.println("Now you have " + size + " tasks in the list.");
        generateDivider();
    }

    /**
     * Prints the message after rescheduling a task.
     *
     * @param oldTask The outdated task that is being rescheduled.
     * @param newTask The updated task after rescheduling.
     */
    public void printTaskRescheduled(String oldTask, String newTask) {
        generateDivider();
        System.out.println("Got it. I've rescheduled this task:");
        generateLeftPadding();
        System.out.println(oldTask);
        generateLeftPadding();
        generateLeftPadding();
        System.out.println("To:");
        generateLeftPadding();
        System.out.println(newTask);
        generateDivider();
    }

    /**
     * Prints the output message after deleting a task.
     *
     * @param message String representation of the task being deleted.
     * @param size New size of the task list after deleting the task.
     */
    public void printTaskDeleted(String message, int size) {
        generateDivider();
        System.out.println("Noted. I've removed this task:");
        generateLeftPadding();
        System.out.println(message);
        System.out.println("Now you have " + size + " tasks in the list.");
        generateDivider();
    }

    /**
     * Prints the tasks that matches keywords after using a Find command.
     *
     * @param tempArrayList ArrayList containing the tasks matched.
     */
    public void printMatchingTasks(ArrayList<Task> tempArrayList) {
        generateDivider();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tempArrayList.size(); i++) {
            int index = i + 1;
            generateLeftPadding();
            System.out.println(index + ". " + tempArrayList.get(i).toString());
        }
        generateDivider();
    }

    /**
     * String representation of the welcome message, used by the GUI.
     *
     * @return String representation of the welcome message.
     */
    public String getWelComeMessage() {
        String logo = "    _____                 ________  .__\n"
                + "   /     \\_______  ______ \\______ \\ |__| ____   ____\n"
                + "  /  \\ /  \\_  __ \\/  ___/  |    |  \\|  |/    \\ /  _ \\ \n"
                + " /    Y    \\  | \\/\\___ \\   |    |   \\  |   |  (  <_> ) \n"
                + " \\____|__  /__|  /____  > /_______  /__|___|  /\\____/ \n"
                + "         \\/           \\/          \\/        \\/ \n";
        String hello = "Hello from \n";
        String welcome = "Welcome!\n";
        String helpUser = "What can Mrs Dino do for you?\n";
        return hello + logo + welcome + helpUser;
    }
}
