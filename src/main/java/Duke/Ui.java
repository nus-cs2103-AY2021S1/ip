package duke;

import java.util.Scanner;

/**
 * A class that handles interactions with the user.
 */
public class Ui {

    private Scanner sc;
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private final String BAR = "____________________________________________________________";
    private final String GREETING = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private final String GOODBYE = "Bye. Hope to see you again soon!";
    private final String DONE = "Nice! I've marked this task as done:";
    private final String ADD = "Got it. I've added this task:";
    private final String DELETE = "Noted. I've removed this task:";
    private final String FAILED_INITIALISE = "Failed to read existing TODO list. Duke will initialise blankly.";
    private final String FOUND_MESSAGE = "Here are the matching tasks in your list:";

    /**
     * Constructs an Ui object capable of collecting user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Gets the user input. Stops accepting input once user inputs "bye".
     * @return  A String of the user input.
     */
    public String getUserInput() {
        String input = this.sc.nextLine();
        if (input.equals("bye")) {
            this.sc.close();
        }
        return input;
    }

    /**
     * Sends Duke's Initialisation message.
     */
    public void sendInitialiseMessage() {
        System.out.println("Hello from\n" + this.LOGO);
    }

    /**
     * Sends Duke's greeting.
     */
    public void greet() {
        System.out.println(this.GREETING);
    }

    /**
     * Sends a long bar of "-"s to space out outputs.
     */
    public void sendBar() {
        System.out.println(this.BAR);
    }

    /**
     * Sends Duke's goodbye message.
     */
    public void bidFarewell() {
        System.out.println(this.GOODBYE);
    }

    /**
     * Sends Duke's message after adding a task.
     * @param taskString  String representation of the task added.
     */
    public void sendAddTaskMessage(String taskString) {
        System.out.println(this.ADD);
        System.out.println("  " + taskString);
    }

    /**
     Sends Duke's message after marking a task as done.
     * @param taskString  String representation of the task marked as done.
     */
    public void sendMarkedAsDoneMessage(String taskString) {
        System.out.println(this.DONE);
        System.out.println("  " + taskString);
    }

    /**
     * Sends Duke's message after deleting a task.
     * @param taskString  String representation of the task deleted.
     */
    public void sendDeleteTaskMessage(String taskString) {
        System.out.println(this.DELETE);
        System.out.println("  " + taskString);
    }

    /**
     * Sends Duke's message if Duke fails to load an existing TaskList from the file.
     */
    public void sendFailedInitialiseMessage() {
        System.out.println(this.FAILED_INITIALISE);
    }

    /**
     * Sends Duke's message when Duke encounters an exception.
     * @param e  Exception encountered.
     */
    public void sendExceptionMessage(Exception e) {
        System.out.println(e);
    }

    /**
     * Overloaded method that sends Duke's message when Duke encounters an exception with given String message to send.
     * @param message  Message that Duke will send.
     */
    public void sendExceptionMessage(String message) {
        System.out.println(message);
    }

    /**
     * Sends Duke's formatted list of tasks.
     * @param tasks  TaskList to be sent.
     */
    public void listTasks(TaskList tasks) {
        System.out.println(tasks);
    }

    /**
     * Sends Duke's formatted tasks that contain the keyword.
     * @param tasks  TaskList to be queried and formatted.
     * @param keyword  Keyword to be queried.
     */
    public void findTasks(TaskList tasks, String keyword) {
        System.out.println(this.FOUND_MESSAGE);
        System.out.println(tasks.findTasks(keyword));
    }

    /**
     * Sends Duke's message of the current number of tasks in the TaskList.
     * @param tasks  TaskLists with tasks to be counted.
     */
    public void sendCount(TaskList tasks) {
        int count = tasks.getCount();
        System.out.println("Now you have " + count + (count==1?" task ":" tasks ") + "in the list.");
    }
}
