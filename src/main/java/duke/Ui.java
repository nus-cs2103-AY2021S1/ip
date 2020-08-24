package duke;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface of the Duke program, dealing with input and output.
 */
public class Ui {

    private Scanner sc;
    private String tab = "     ";
    private String line = "____________________________________________________________";

    /**
     * Initializes a newly created Ui with a Scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the user input.
     * @return user input.
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Greets the user.
     */
    public void greet() {
        String tab = "     ";
        chatPrint("Hello! I'm Duke\n" +
                tab + "What can I do for you?");
    }

    /**
     * Prints a farewell message to the user.
     */
    public void farewell() {
        chatPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the set output whenever the list command is called.
     * @param tasks list of tasks.
     */
    public void onList(List<Task> tasks) {
        int id = 1;
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (Task task : tasks) {
            output.append("\n").append(tab).append(id).append(". ").append(task);
            id++;
        }
        chatPrint(output.toString());
    }

    /**
     * Prints the set output whenever a task is done.
     * @param task a done task.
     */
    public void onDone(Task task) {
        chatPrint("Nice! I've marked this task as done:\n" +
                tab + "   " + task);
    }

    /**
     * Prints the set output whenever a task is removed.
     * @param task a removed task.
     * @param size size of the list.
     */
    public void onDelete(Task task, int size) {
        chatPrint("Noted. I've removed this task:\n" +
                tab + "   " + task + "\n" +
                tab + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the set output whenever a task is added.
     * @param task an added task.
     * @param size size of the list.
     */
    public void onAdd(Task task, int size) {
        chatPrint("Got it. I've added this task:\n" +
                tab + "   " + task + "\n" +
                tab + "Now you have " + size + " tasks in the list.");    }

    /**
     * Formats and prints a string.
     * @param toPrint string to print.
     */
    public void chatPrint(String toPrint) {
        System.out.println(tab + line);
        System.out.println(tab + toPrint);
        System.out.println(tab + line + "\n");
    }
}
