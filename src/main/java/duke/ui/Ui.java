package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Tasks;

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
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n\n";
        String greeting = logo + " Hello! I'm Duke\n" + " What can I do for you?";
        return greeting;
    }
    
    /**
     * Returns exit message.
     *
     * @return the exit message.
     */
    public String getExitMessage() {
        String bye = "Bye. Hope to see you again soon!";
        return bye;
    }

    /**
     * Returns add task message.
     *
     * @param task   the task.
     * @param length the length of the tasks.
     * @return the add task message.
     */
    public String getAddTaskMessage(Task task, int length) {
        String message = " Got it. I've added this task:\n";
        message += " " + task;
        message += String.format("\n Now you have %s task%s in the list.", length, length > 1 ? "s" : "");
        return message;
    }
    
    /**
     * Returns list message.
     *
     * @param tasks the tasks.
     * @return the list message.
     */
    public String getListMessage(Tasks tasks) {
        StringBuilder builder = new StringBuilder(" Here are the tasks in your list:\n");
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
            builder.append(String.format(" %s. %s\n", i + 1, list.get(i)));
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
            String message = String.format(" I couldn't find anything on %s.", formattedDate);
            return message;
        } else {
            String intro = String.format(" Here are the tasks that I've found on %s:\n", formattedDate);
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
            String message = String.format(" I couldn't find anything matching %s.", description);
            return message;
        } else {
            String intro = String.format(" Here are the tasks that I've found matching %s:\n", description);
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
        return "Nice! I've marked this task as done:\n " + task;
    }

    /**
     * Returns delete task message.
     *
     * @param task   the task.
     * @param length the length of the tasks.
     * @return the delete task message.
     */
    public String getDeleteTaskMessage(Task task, int length) {
        String message = "Noted. I've removed this task:\n " + task;
        message += String.format("\nNow you have %s task%s in the list.", length, length > 1 ? "s" : "");
        return message;
    }

    /**
     * Returns message representing duke exception.
     *
     * @param ex the duke exception.
     * @return the duke exception message.
     */
    public String getDukeExceptionMessage(DukeException ex) {
        return "ERROR: " + ex.getMessage();
    }
}
