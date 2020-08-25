package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {

    /** Scanner to read user inputs. */
    Scanner scanner;

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints greeting message.
     */
    public void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String intro1 = "Hello! I'm Duke \n";
        String intro2 = "What can I do for you? \n";

        String greeting = formatString(intro1 + intro2);
        System.out.println(greeting);
    }

    /**
     * Adds dividers to the message.
     * @param s Message to add dividers to.
     * @return Message with dividers.
     */
    private String addDividers(String s) {
        String divider = "____________________________________________________________________\n";
        String dividerFormatted = String.format("%" + (5 + divider.length()) + "s", divider);
        return dividerFormatted + s + dividerFormatted;
    }

    /**
     * Formats string to follow indent alignment.
     * @param s String to be indented.
     * @return Indented string.
     */
    public String formatString(String s) {
        String output = "";
        Scanner scanner = new Scanner(s);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String formattedLine = String.format("%" + (6 + line.length()) + "s", line);
            output = output + formattedLine + "\n";
        }
        return addDividers(output);
    }

    /**
     * Formats string and then prints the formmatted string.
     * @param s String to be printed.
     */
    public void printString(String s) {
        System.out.println(formatString(s));
    }

    /**
     * Prints message that task was added to TaskList.
     * @param task Task that was added.
     * @param size Size of TaskList.
     */
    public void printAddTask(Task task, int size) {
        String s = "Got it. I've added this task: \n" +
                task.toString() + '\n' +
                "Now you have " + size + " tasks in the list. \n";
        System.out.println(formatString(s));
    }

    /**
     * Prints goodbye message.
     */
    public void printGoodbye() {
        String goodbye = "Bye. Hope to see you again soon! \n";
        System.out.println(formatString(goodbye));
    }

    /**
     * Prints message that task has been deleted from TaskList.
     * @param task Task that was deleted from TaskList.
     * @param size Size of TaskList.
     */
    public void printDeletedTask(Task task, int size) {
        String success = "Noted. I've removed this task: \n"
                + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list. \n";
        System.out.println(formatString(success));

    }

    /**
     * Prints message that task has been marked as done.
     * @param task Task that is marked as done.
     */
    public void printMarkedTask(Task task) {
        String success = "Nice! I've marked this task as done: \n"
                + task + "\n";
        System.out.println(formatString(success));
    }

    /**
     * Prints TaskList.
     * @param list TaskList to be printed.
     */
    public void printList(List<Task> list) {
        String printedList = "";
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                String lineItem = (i + 1) + ". " + list.get(i) + "\n";
                printedList = printedList + lineItem;
            }
        } else {
            printedList = "List is empty \n";
        }
        System.out.println(formatString(printedList));
    }

    /**
     * Reads in user input.
     * @return User input as String.
     */
    public String readInput() {
        return scanner.nextLine();
    }
}
