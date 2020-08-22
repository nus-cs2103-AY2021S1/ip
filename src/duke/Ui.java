package duke;

import exception.DukeException;
import exception.NoSuchTaskException;

import task.Task;

import java.util.Scanner;

/**
 * Represents a <code>Ui</code> that deals with interactions with the user.
 */
public class Ui {
    /**
     * Displays <code>text</code>.
     *
     * @param text Text to be displayed.
     */
    public void say(String text) {
        System.out.println(text);
    }

    /**
     * Displays the <code>String</code> representation of <code>e</code>.
     *
     * @param e Exception whose <code>String</code> representation will be displayed.
     */
    public void sayException(Exception e) {
        System.out.println(e);
    }

    /**
     * Displays a line of "-".
     */
    public void showLine() {
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Displays a hello message.
     */
    public void hello() {
        say("Hello! I'm duke\nWhat can I do for you?");
    }

    /**
     * Displays an exiting message.
     */
    public void bye() {
        say("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating the <code>Task</code> has been added.
     */
    public void sayAddedTask(Task task, int tasksTotal) {
        say("Got it. I've added this task:\n" + task + "\nNow you have " + tasksTotal + " tasks in the list.");
    }

    /**
     * Displays a message indicating the <code>Task</code> has been deleted.
     */
    public void sayDeletedTask(Task task, int tasksTotal) {
        say("Noted. I've removed this task:\n" + task + "\nNow you have " + tasksTotal + " tasks in the list.");
    }

    /**
     * Displays a message indicating the <code>Task</code> has been marked as done.
     */
    public void sayMarkedTask(Task task) {
        say( "Nice! I've marked this task as done:\n" + task);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Displays all <code>Task</code> inside <code>tasks</code>.
     */
    public void displayTasks(TaskList tasks) {
        String text = "";
        for (int i = 0; i < tasks.size(); i++) {
            boolean isLastTask = i == tasks.size() - 1;
            try {
                text += ((i + 1) + ". " + tasks.get(i).toString() + (isLastTask ? "" : "\n"));
            } catch (NoSuchTaskException e) {
                sayException(e);
            }
        }
        say(text);
    }
}
