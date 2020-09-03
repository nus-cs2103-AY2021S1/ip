package pandabot.ui;

import java.util.Scanner;

import pandabot.tasks.Task;
import pandabot.tasks.TaskList;

/**
 *  Represents the user interface which is responsible for generating and printing the
 *  messages that is used to interact with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Creates a Ui object.
     */
    public Ui () {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome logo.
     */
    public void printWelcome() {
        String logo =
                " ____                    _\n"
                        + "|  _ \\                  | |\n"
                        + "| |_| |___  _ _  __  ___| | ___  _\n"
                        + "| ___/  _ \\| | |/  |/ _   |/ _ \\| |\n"
                        + "| |  | |_|   |  _  | |_|  | |_|   |\n"
                        + "|_|  \\____,__|_| |_|\\___,_|\\___,__|" + " bot\n\n";

        System.out.println(logo + displayWelcomeMessage());
    }

    public String displayWelcomeMessage() {
        return "Hello! I'm PandaBot.\n" + "What can I do for you?\n";
    }

    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        System.out.println(" ___________________________________________________");
    }

    /**
     * Returns a String representation of the user input.
     *
     * @return a String representation of the user input
     */
    public String readCmd() {
        return scanner.nextLine();
    }

    /**
     * Prints a message.
     *
     * @param message the message to be printed
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Returns a String representation of the bye message.
     *
     * @return a String representation of the bye message
     */
    public String displayOnExit() {
        scanner.close();
        return "Bye! Remember to finish the rest of your work! See you soon~";
    }

    /**
     * Returns a String representation of the list tasks message.
     *
     * @param tasks the TaskList to be printed
     * @return a String representation of the list tasks message
     */
    public String displayOnList(TaskList tasks) {
        int len = tasks.size();
        if (len == 0) {
            return "WOOTS! You don't have any tasks to do at the moment.";
        } else {
            return "These are the task(s) you have:\n" + tasks.toString();
        }
    }

    /**
     * Returns a String representation of the done message.
     *
     * @param task the task that is done
     * @return a String representation of the done message
     */
    public String displayOnDone(Task task) {
        return "Great! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a String representation of the delete message.
     *
     * @param task the Task to be deleted
     * @param numOfTasks the number of tasks in the list
     * @return a String representation of the delete message
     */
    public String displayOnDelete(Task task, int numOfTasks) {
        return "Will do! I've removed this task:\n" + task + "\n" +
                "Now you have " + numOfTasks + " task(s) in this list.";
    }

    /**
     * Returns a String representation of the add task message.
     *
     * @param task the Task to be printed
     * @param numOfTasks the number of tasks in the list
     * @return a String representation of the add task message
     */
    public String displayOnAddTask(Task task, int numOfTasks) {
        return "Noted! I've added this task:\n" + task.toString() + "\n" +
                "Now you have " + numOfTasks + " task(s) in this list.";
    }

    /**
     * Returns a String representation of the list of tasks with matching description.
     *
     * @param tasks the TaskList which contains the tasks with matching description.
     * @return a String representation of the list of tasks with matching description
     */
    public String displayOnFind(TaskList tasks) {
        return "Here are the matching tasks I found:\n" + tasks.toString();
    }

}
