package duke.util;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the user interface of the Duke program, dealing with input and output.
 */
public class Ui {

    private final Scanner sc;

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
        printWithFormatting(textToPrint);
        return textToPrint;
    }

    /**
     * Prints a goodbye message to the user.
     *
     * @return the printed message.
     */
    public String sayBye() {
        String textToPrint = "Bye. Hope to see you again soon!";
        printWithFormatting(textToPrint);
        return textToPrint;
    }

    /**
     * Prints the set output whenever the list command is called.
     *
     * @param tasks list of tasks.
     * @return the printed message.
     */
    public String printList(List<Task> tasks) {
        assert tasks != null;
        int id = 1;
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (Task task : tasks) {
            output.append("\n").append(id).append(". ").append(task);
            id++;
        }
        printWithFormatting(output.toString());
        assert id == tasks.size() + 1;
        return output.toString();
    }

    /**
     * Prints the set output whenever a find command is executed.
     *
     * @param tasks a list of tasks.
     * @return the printed message.
     */
    public String printFoundTasks(List<Task> tasks) {
        assert tasks != null;
        int id = 1;
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            output.append("\n").append(id).append(". ").append(task);
            id++;
        }
        printWithFormatting(output.toString());
        assert id == tasks.size() + 1;
        return output.toString();
    }

    /**
     * Prints the set output whenever a task is done.
     *
     * @param task a done task.
     * @return the printed message.
     */
    public String printDoneMessage(Task task) {
        assert task != null;
        String textToPrint = "Nice! I've marked this task as done:\n"
                + "   " + task;
        printWithFormatting(textToPrint);
        return textToPrint;
    }

    /**
     * Prints the set output whenever a task is removed.
     *
     * @param task a removed task.
     * @param size size of the list.
     * @return the printed message.
     */
    public String printDeleteMessage(Task task, int size) {
        assert task != null && size >= 0;
        String textToPrint = "Noted. I've removed this task:\n"
                + "   " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
        printWithFormatting(textToPrint);
        return textToPrint;
    }

    /**
     * Prints the set output whenever a task is added.
     *
     * @param task an added task.
     * @param size size of the list.
     * @return the printed message.
     */
    public String printAddMessage(Task task, int size) {
        assert task != null && size >= 0;
        String textToPrint = "Got it. I've added this task:\n"
                + "   " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
        printWithFormatting(textToPrint);
        return textToPrint;
    }

    /**
     * Formats and prints a string.
     *
     * @param toPrint string to print.
     */
    public void printWithFormatting(String toPrint) {
        assert toPrint != null;
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(toPrint);
        System.out.println(line + "\n");
    }
}
