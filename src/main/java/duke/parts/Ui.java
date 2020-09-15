package duke.parts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * A class to handle the response and output that is being shown to the user.
 *
 * @author Roger Lim
 */
public class Ui {
    static final String LINE = "    ____________________________________________________________";
    static final String INDENT = "    ";
    private Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(INDENT + "Hello! What can I do for you?");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    private String printNumTask(int numTask) {
        if (numTask == 1) {
            return String.format("You have %d task in the list.", numTask);
        }
        return String.format("You have %d tasks in the list.", numTask);
    }

    /**
     * Shows the response after an item is deleted.
     * @param removed Task that have been removed.
     * @param numLeft Remaining number of tasks in the system
     * @return The message for the user
     */
    public String showDelete(Task removed, int numLeft) {
        String taskLeft = printNumTask(numLeft);
        String output = "Task removed: " + "\n"
                                + INDENT + removed.getOutput() + "\n"
                                + taskLeft;
        System.out.println(output);
        return output;
    }

    /**
     * Prints the list of task in the system
     * @param storage Storage of the system.
     * @return The message for the user
     * @throws IOException
     */
    public String printList(Storage storage) throws IOException {
        ArrayList<Task> arrTask = storage.load();
        String output = printNumTask(arrTask.size()) + "\n";
        output += "Here are the tasks in your list:\n";
        for (int i = 0; i < arrTask.size(); i++) {
            output += String.format("%s%d. %s", INDENT, i + 1, arrTask.get(i).getOutput());
            if (i < arrTask.size() - 1) {
                output += "\n";
            }
        }
        System.out.println(output);
        return output;
    }

    /**
     * Shows the response after an item is added.
     * @param task Task that is added.
     * @param type The type of task.
     * @param numTask Number of task in the system before adding this task.
     * @return The message for the user
     */
    public String printNew(Task task, String type, int numTask) {
        String output = String.format("Adding %s to the list:\n", type)
                        + INDENT + String.format("%s\n", task.getOutput())
                                + printNumTask(numTask);
        System.out.println(output);
        return output;
    }

    /**
     * Shows the tasks that macthes the search criteria.
     * @param arr Arraylist of items that has been found.
     * @return The message for the user
     */
    public String printFind(ArrayList<Task> arr) {
        if (arr.isEmpty()) {
            System.out.println("There are no items");
            return "There are no items";
        } else {
            String output = "Here are the items that match\n";
            for (int i = 0; i < arr.size(); i++) {
                output += String.format("%s%d) %s", INDENT, i + 1,
                        arr.get(i).getOutput()) + "\n";
            }
            System.out.println(output);
            return output;
        }
    }

    /**
     * Prints the closing message.
     * @return The closing message.
     */
    public String bye() {
        System.out.println("Bye. Hope to see you again soon");
        return "Bye. Hope to see you again soon";
    }

    /**
     * Prints the message when task is marked as done.
     * @param task
     * @return Message for the user.
     */
    public String printDone(Task task) {
        String output = "Nice! I've marked this task as done: \n" + INDENT;
        output += task.getOutput();
        System.out.println(output);
        return output;
    }

    /**
     * Prints the message when tasks are sorted.
     * @return Message for the user.
     */
    public String printSorted() {
        String output = "Tasks sorted";
        System.out.println(output);
        return output;
    }
}
