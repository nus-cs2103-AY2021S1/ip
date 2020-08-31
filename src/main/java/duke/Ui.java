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
    String intro() {
        return reply("Hello, I'm Ravenloss") + "\n"
                + reply("What can I do for you?");
    }

    /**
     * Displays the message with the indentation.
     * @param string String to be displayed.
     */
    public String reply(String string) {
        return INDENTATION + string;
    }

//    /**
//     * Gives the next command line inputted by the user.
//     * @return The next inquiry.
//     */
//    public String nextInquiry() {
//        return scanner.nextLine();
//    }

    /**
     * Displays the list of tasks
     * @param tasks The list of tasks.
     */
    public String list(List<Task> tasks) {
        StringBuilder buffer = new StringBuilder();
        if (tasks.size() == 0) {
            buffer.append(reply("You have no pending tasks"));
        } else {
            buffer.append(reply("Here are the tasks in your list:")).append("\n");
            for (int i = 0; i < tasks.size(); i++) {
                String number = (i + 1) + ".";
                Task currentTask = tasks.get(i);
                buffer.append(reply(number + currentTask.toString())).append("\n");
            }
        }
        return buffer.toString();
    }

    /**
     * Display the filtered list with the proper response message to the user.
     * @param filteredTasks The filtered tasks.
     */
    public String filteredList(List<Task> filteredTasks) {
        StringBuilder buffer = new StringBuilder();
        if (filteredTasks.size() == 0) {
            buffer = new StringBuilder(reply("No tasks found"));
        } else {
            buffer.append(reply("Here are the matching tasks in your list:")).append("\n");
            for (int i = 0; i < filteredTasks.size(); i++) {
                String number = (i + 1) + ".";
                Task currentTask = filteredTasks.get(i);
                buffer.append(reply(number + currentTask.toString())).append("\n");
            }
        }
        return buffer.toString();
    }

    /**
     * Displays the message when a task is added to the list.
     * @param currentTask The task to be added.
     * @param size The size of the list.
     */
    public String addMessage(Task currentTask, Integer size) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(reply("Got it. I've added this duke.task:")).append("\n")
                .append(reply(INDENTATION + currentTask.toString())).append("\n")
                .append(reply("Now you have " + size + " tasks in the list.")).append("\n");
        return buffer.toString();
    }

    /**
     * Displays the message when a task is marked as done.
     * @param currentTask The tasks that was marked as done.
     */
    public String doneMessage(Task currentTask) {
        currentTask.done();
        StringBuilder buffer = new StringBuilder();
        buffer.append(reply("Good job! I've marked this task as done")).append("\n")
                .append(reply(INDENTATION + currentTask.toString())).append("\n");
        return buffer.toString();
    }

    /**
     * Displays the farewell message.
     */
    public String farewell() {
        return reply("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the message after deleting a task.
     * @param currentTask The task deleted.
     * @param sizeLeft The size of the list after deleting the task.
     */
    public String deleteMessage(Task currentTask, Integer sizeLeft) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(reply("Noted. I've removed this task: ")).append("\n")
                .append(reply(INDENTATION + currentTask.toString())).append("\n")
                .append(reply("Now you have " + sizeLeft + " tasks in the list."))
                .append("\n");
        return buffer.toString();
    }

    /**
     * Displays the dotted line to separate the user input and the system generated output.
     */
    public void showLine() {
        System.out.println(LINE);
    }


}
