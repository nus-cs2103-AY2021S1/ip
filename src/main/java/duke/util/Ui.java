package duke.util;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the user interface of the Duke program, dealing with input and output.
 */
public class Ui {

    private Scanner sc;
    private String line = "____________________________________________________________";

    /**
     * Initializes a newly created Ui with a Scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the user input.
     *
     * @return user input.
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Greets the user.
     *
     * @return the printed message.
     */
    public String greet() {
        String textToPrint = "Hello! I'm Duke\n"
                + "What can I do for you?";
        chatPrint(textToPrint);
        return textToPrint;
    }

    /**
     * Prints a farewell message to the user.
     *
     * @return the printed message.
     */
    public String farewell() {
        String textToPrint = "Bye. Hope to see you again soon!";
        chatPrint(textToPrint);
        return textToPrint;
    }

    /**
     * Prints the set output whenever the list command is called.
     *
     * @param tasks list of tasks.
     * @return the printed message.
     */
    public String onList(List<Task> tasks) {
        int id = 1;
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (Task task : tasks) {
            output.append("\n").append(id).append(". ").append(task);
            id++;
        }
        chatPrint(output.toString());
        return output.toString();
    }

    /**
     * Prints the set output whenever a find command is executed.
     *
     * @param tasks a list of tasks.
     * @return the printed message.
     */
    public String onFind(List<Task> tasks) {
        int id = 1;
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            output.append("\n").append(id).append(". ").append(task);
            id++;
        }
        chatPrint(output.toString());
        return output.toString();
    }

    /**
     * Prints the set output whenever a task is done.
     *
     * @param task a done task.
     * @return the printed message.
     */
    public String onDone(Task task) {
        String textToPrint = "Nice! I've marked this task as done:\n"
                + "   " + task;
        chatPrint(textToPrint);
        return textToPrint;
    }

    /**
     * Prints the set output whenever a task is removed.
     *
     * @param task a removed task.
     * @param size size of the list.
     * @return the printed message.
     */
    public String onDelete(Task task, int size) {
        String textToPrint = "Noted. I've removed this task:\n"
                + "   " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
        chatPrint(textToPrint);
        return textToPrint;
    }

    /**
     * Prints the set output whenever a task is added.
     *
     * @param task an added task.
     * @param size size of the list.
     * @return the printed message.
     */
    public String onAdd(Task task, int size) {
        String textToPrint = "Got it. I've added this task:\n"
                + "   " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
        chatPrint(textToPrint);
        return textToPrint;
    }

    /**
     * Formats and prints a string.
     *
     * @param toPrint string to print.
     */
    public void chatPrint(String toPrint) {
        System.out.println(line);
        System.out.println(toPrint);
        System.out.println(line + "\n");
    }
}
