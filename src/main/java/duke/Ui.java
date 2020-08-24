package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Tasks;

/**
 * The Ui interacts with the user..
 */
public class Ui {
    /**
     * The Scanner to read inputs.
     */
    private final Scanner scanner;

    /**
     * Instantiates a new Ui and initialises the scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Print welcome message.
     */
    public void showWelcome() {
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n\n";
        String greeting = logo + " Hello! I'm Duke\n" + " What can I do for you?";
        printWithDashes(greeting);
    }

    /**
     * Print the string with dashes.
     *
     * @param str the str.
     */
    public void printWithDashes(String str) {
        printDashes();
        System.out.println(str);
        printDashes();
    }

    /**
     * Print dashes.
     */
    private void printDashes() {
        int length = 60;
        System.out.println("_".repeat(length));
    }

    /**
     * Print exit message.
     */
    public void printExitMessage() {
        String bye = "Bye. Hope to see you again soon!";
        printWithDashes(" " + bye);
    }

    /**
     * Print add task.
     *
     * @param task   the task.
     * @param length the length of the tasks.
     */
    public void printAddTask(Task task, int length) {
        String message = " Got it. I've added this task:\n";
        message += " " + task;
        message += String.format("\n Now you have %s task%s in the list.", length, length > 1 ? "s" : "");
        printWithDashes(message);
    }

    /**
     * Print list.
     *
     * @param tasks the tasks.
     */
    public void printList(Tasks tasks) {
        StringBuilder builder = new StringBuilder(" Here are the tasks in your list:\n");
        printWithDashes(printListItems(builder, tasks.getTasks()).toString());
    }

    /**
     * Returns list items to be printed.
     *
     * @param builder the string builder.
     * @param list    the list.
     * @return the string builder.
     */
    private StringBuilder printListItems(StringBuilder builder, ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            builder.append(String.format(" %s. %s\n", i + 1, list.get(i)));
        }
        return builder;
    }

    /**
     * Print tasks found with the date.
     *
     * @param localDate the date.
     * @param list      the list.
     */
    public void printFound(LocalDate localDate, ArrayList<Task> list) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = localDate.format(formatter);
        if (list.size() == 0) {
            String message = String.format(" I couldn't find anything on %s.", formattedDate);
            printWithDashes(message);
        } else {
            String intro = String.format(" Here are the tasks that I've found on %s:\n", formattedDate);
            StringBuilder builder = new StringBuilder(intro);
            printWithDashes(printListItems(builder, list).toString());
        }
    }

    /**
     * Print tasks found with the description.
     *
     * @param description the description.
     * @param list        the list.
     */
    public void printFound(String description, ArrayList<Task> list) {
        if (list.size() == 0) {
            String message = String.format(" I couldn't find anything matching %s.", description);
            printWithDashes(message);
        } else {
            String intro = String.format(" Here are the tasks that I've found matching %s:\n", description);
            StringBuilder builder = new StringBuilder(intro);
            printWithDashes(printListItems(builder, list).toString());
        }
    }

    /**
     * Print mark task as done.
     *
     * @param task the task.
     */
    public void printMarkTaskAsDone(Task task) {
        printWithDashes(" Nice! I've marked this task as done:\n " + task);
    }

    /**
     * Print delete task.
     *
     * @param task   the task.
     * @param length the length of the tasks.
     */
    public void printDeleteTask(Task task, int length) {
        String message = " Noted. I've removed this task:\n " + task;
        message += String.format("\n Now you have %s task%s in the list.", length, length > 1 ? "s" : "");
        printWithDashes(message);
    }

    /**
     * Print duke exception.
     *
     * @param ex the exception.
     */
    public void printDukeException(DukeException ex) {
        printWithDashes(" ERROR: " + ex.getMessage());
    }

    /**
     * Returns the input by reading commands.
     *
     * @return the input.
     */
    public String readCommand() {
        if (this.scanner.hasNextLine()) {
            return this.scanner.nextLine();
        } else {
            this.scanner.close();
            return "bye";
        }
    }
}
