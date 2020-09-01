package blue.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import blue.exception.DukeException;
import blue.task.Task;
import blue.task.Tasks;

/**
 * The Ui generates messages.
 */
public class Ui {
    /**
     * Returns welcome message.
     *
     * @return the welcome message.
     */
    public String getWelcomeMessage() {
        String greeting = "Hello, I'm Blue\n\n";
        greeting += "Starry, starry night\n"
                + "Paint your palette blue and grey\n"
                + "Look out on a summer's day\n"
                + "With eyes that know the darkness in my soul\n"
                + "Shadows on the hills\n"
                + "Sketch the trees and the daffodils\n"
                + "Catch the breeze and the winter chills\n"
                + "In colors on the snowy linen land\n";
        return greeting;
    }

    /**
     * Returns exit message.
     *
     * @return the exit message.
     */
    public String getExitMessage() {
        return "Bye. See you again!\n";
    }

    /**
     * Returns the long exit message.
     *
     * @return the exit message
     */
    public String getLongExitMessage() {
        return "\nBut I could have told you, Vincent\n"
                + "This world was never meant for one\n"
                + "As beautiful as you\n";
    }

    /**
     * Returns add task message.
     *
     * @param task   the task.
     * @param length the length of the tasks.
     * @return the add task message.
     */
    public String getAddTaskMessage(Task task, int length) {
        String message = "I've added this task:\n";
        message += " " + task;
        message += String.format("\nNow you have %s task%s in the list.", length, length > 1 ? "s" : "");
        return message;
    }

    /**
     * Returns list message.
     *
     * @param tasks the tasks.
     * @return the list message.
     */
    public String getListMessage(Tasks tasks) {
        StringBuilder builder = new StringBuilder("Here are the tasks in your list:\n");
        return this.getListItems(builder, tasks.getTasks()).toString();
    }

    /**
     * Returns list items to be printed.
     *
     * @param builder the string builder.
     * @param list    the list.
     * @return the string builder.
     */
    private StringBuilder getListItems(StringBuilder builder, ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            builder.append(String.format("%s. %s\n", i + 1, list.get(i)));
        }
        return builder;
    }

    /**
     * Returns tasks found with the date.
     *
     * @param localDate the date.
     * @param list      the list.
     * @return the found message.
     */
    public String getFoundMessage(LocalDate localDate, ArrayList<Task> list) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = localDate.format(formatter);
        if (list.isEmpty()) {
            return String.format("I couldn't find anything on %s.", formattedDate);
        } else {
            String intro = String.format("Here are the tasks that I've found on %s:\n", formattedDate);
            StringBuilder builder = new StringBuilder(intro);
            return this.getListItems(builder, list).toString();
        }
    }

    /**
     * Returns tasks found with the description.
     *
     * @param description the description.
     * @param list        the list.
     * @return the found message.
     */
    public String getFoundMessage(String description, ArrayList<Task> list) {
        if (list.isEmpty()) {
            return String.format("I couldn't find anything matching %s.", description);
        } else {
            String intro = String.format("Here are the tasks that I've found matching %s:\n", description);
            StringBuilder builder = new StringBuilder(intro);
            return this.getListItems(builder, list).toString();
        }
    }

    /**
     * Returns mark task as done message.
     *
     * @param task the task.
     * @return the mark task as done message.
     */
    public String getMarkTaskAsDoneMessage(Task task) {
        return "One less thing on your plate! I've marked this task as done:\n " + task;
    }

    /**
     * Returns delete task message.
     *
     * @param task   the task.
     * @param length the length of the tasks.
     * @return the delete task message.
     */
    public String getDeleteTaskMessage(Task task, int length) {
        String message = "I've removed this task:\n " + task;
        message += String.format("\nNow you have %s task%s in the list.", length, length > 1 ? "s" : "");
        return message;
    }

    /**
     * Returns unknown inout message.
     *
     * @return the unknown input message.
     */
    public String getUnknownInputMessage() {
        return "I couldn't understand what you tried to say to me.";
    }

    /**
     * Returns message representing blue exception.
     *
     * @param ex the blue exception.
     * @return the blue exception message.
     */
    public String getDukeExceptionMessage(DukeException ex) {
        return ex.getMessage();
    }
}
