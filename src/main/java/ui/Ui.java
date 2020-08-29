package ui;

import java.util.Scanner;

import duke.TaskList;
import exception.NoSuchTaskException;
import task.Task;

/**
 * Represents a <code>Ui</code> that deals with interactions with the user.
 */
public class Ui {
    /**
     * Displays <code>text</code>.
     *
     * @param text Text to be displayed.
     */
    public String say(String text) {
        return text;
    }

    /**
     * Displays the <code>String</code> representation of <code>e</code>.
     *
     * @param e Exception whose <code>String</code> representation will be displayed.
     */
    public String sayException(Exception e) {
        return e.toString();
    }

    /**
     * Displays a line of "-".
     */
    public String showLine() {
        return "------------------------------------------------------------";
    }

    /**
     * Displays a hello message.
     */
    public String hello() {
        return say("Hello! I'm duke\nWhat can I do for you?");
    }

    /**
     * Displays an exiting message.
     */
    public String bye() {
        return say("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating the <code>Task</code> has been added.
     */
    public String sayAddedTask(Task task, int tasksTotal) {
        return say("Got it. I've added this task:\n" + task + "\nNow you have " + tasksTotal + " tasks in the list.");
    }

    /**
     * Displays a message indicating the <code>Task</code> has been deleted.
     */
    public String sayDeletedTask(Task task, int tasksTotal) {
        return say("Noted. I've removed this task:\n" + task + "\nNow you have " + tasksTotal + " tasks in the list.");
    }

    /**
     * Displays a message indicating the <code>Task</code> has been marked as done.
     */
    public String sayMarkedTask(Task task) {
        return say("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Reads and returns the command input.
     * @return The command input.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Displays all <code>Task</code> inside <code>tasks</code>.
     */
    public String displayTasks(TaskList tasks) {
        String text = "";
        for (int i = 0; i < tasks.size(); i++) {
            boolean isLastTask = i == tasks.size() - 1;
            try {
                text += ((i + 1) + ". " + tasks.get(i).toString() + (isLastTask ? "" : "\n"));
            } catch (NoSuchTaskException e) {
                return sayException(e);
            }
        }
        return tasks.size() == 0 ? say("You do not have any task!") : say(text);
    }
}
