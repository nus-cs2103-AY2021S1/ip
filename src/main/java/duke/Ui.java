package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner scanner;
    protected final String INDENTATION = "     ";
    protected final String LINE = "    ____________________________________________________________";

    /**
     * Ui constructor.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the introductions.
     */
    void intro() {
        reply("Hello, I'm Ravenloss");
        reply("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Displays the message with the indentation.
     * @param string String to be displayed.
     */
    public void reply(String string) {
        System.out.println(INDENTATION + string);
    }

    /**
     * Gives the next command line inputted by the user.
     * @return The next inquiry.
     */
    public String nextInquiry() {
        return scanner.nextLine();
    }

    /**
     * Displays the list of tasks
     * @param tasks The list of tasks.
     */
    public void list(List<Task> tasks) {
        if (tasks.size() == 0) {
            reply("You have no pending tasks");
        } else {
            reply("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                String number = (i + 1) + ".";
                Task currentTask = tasks.get(i);
                reply(number + currentTask.toString());
            }
        }
    }

    /**
     * Displays the message when a task is added to the list.
     * @param currentTask The task to be added.
     * @param size The size of the list.
     */
    public void addMessage(Task currentTask, Integer size) {
        reply("Got it. I've added this duke.task:");
        reply(INDENTATION + currentTask.toString());
        reply("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays the message when a task is marked as done.
     * @param currentTask The tasks that was marked as done.
     */
    public void doneMessage(Task currentTask) {
        currentTask.done();
        reply("Good job! I've marked this task as done");
        reply(INDENTATION + currentTask.toString());
    }

    /**
     * Displays the farewell message.
     */
    public void farewell() {
        reply("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the message after deleting a task.
     * @param currentTask The task deleted.
     * @param sizeLeft The size of the list after deleting the task.
     */
    public void deleteMessage(Task currentTask, Integer sizeLeft) {
        reply("Noted. I've removed this duke.task: ");
        reply(INDENTATION + currentTask.toString());
        reply("Now you have " + sizeLeft + " tasks in the list.");
    }

    /**
     * Displays the dotted line to separate the user input and the system generated output.
     */
    public void showLine() {
        System.out.println(LINE);
    }


}
