package duke;

import java.util.Scanner;

/**
 * A class that handles interactions with the user.
 */
public class Ui {

    private Scanner sc;
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private final String bar = "____________________________________________________________";
    private final String greeting = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private final String goodbye = "Bye. Hope to see you again soon!";
    private final String done = "Nice! I've marked this task as done:";
    private final String add = "Got it. I've added this task:";
    private final String delete = "Noted. I've removed this task:";
    private final String failedInitialise = "Failed to read existing TODO list. Duke will initialise blankly.";
    private final String foundMessage = "Here are the matching tasks in your list:";
    private final String undoSuccessMessage = "Undo successful.";
    private final String undoFailMessage = "Undo unsuccessful.";

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
        System.out.println("Hello from\n" + this.logo);
    }

    public String getInitialiseMessage() {
        return "Hello from\n" + this.logo;
    }

    /**
     * Sends Duke's greeting.
     */
    public void greet() {
        System.out.println(this.greeting);
    }

    public String getGreeting() {
        return this.greeting;
    }
    /**
     * Sends a long bar of "-"s to space out outputs.
     */
    public void sendBar() {
        System.out.println(this.bar);
    }


    /**
     * Sends Duke's goodbye message.
     */
    public void bidFarewell() {
        System.out.println(this.goodbye);
    }

    /**
     * Sends Duke's message after adding a task.
     * @param taskString  String representation of the task added.
     */
    public void sendAddTaskMessage(String taskString) {
        assert taskString.length() != 0 : "trying to send empty taskString";
        System.out.println(this.add);
        System.out.println("  " + taskString);
    }

    public String getAddTaskMessage(String taskString) {
        assert taskString.length() != 0 : "trying to send empty taskString";
        return this.add + "\n"
                + "  " + taskString;
    }

    /**
     Sends Duke's message after marking a task as done.
     * @param taskString  String representation of the task marked as done.
     */
    public void sendMarkedAsDoneMessage(String taskString) {
        assert taskString.length() != 0 : "trying to send empty taskString";
        System.out.println(this.done);
        System.out.println("  " + taskString);
    }

    public String getMarkedAsDoneMessage(String taskString) {
        assert taskString.length() != 0 : "trying to send empty taskString";
        return this.done + "\n"
                + "  " + taskString;
    }

    /**
     * Sends Duke's message after deleting a task.
     * @param taskString  String representation of the task deleted.
     */
    public void sendDeleteTaskMessage(String taskString) {
        assert taskString.length() != 0 : "trying to send empty taskString";
        System.out.println(this.delete);
        System.out.println("  " + taskString);
    }
    public String getDeleteTaskMessage(String taskString) {
        assert taskString.length() != 0 : "trying to send empty taskString";
        return this.delete + "\n"
                + "  " + taskString;
    }

    /**
     * Sends Duke's message if Duke fails to load an existing TaskList from the file.
     */
    public void sendFailedInitialiseMessage() {
        System.out.println(this.failedInitialise);
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

    public String getExceptionMessage(Exception e) {
        return e.toString();
    }

    public String getExceptionMessage(String message) {
        return message;
    }

    /**
     * Sends Duke's formatted list of tasks.
     * @param tasks  TaskList to be sent.
     */
    public void listTasks(TaskList tasks) {
        assert tasks != null : "listTasks tasks is null";
        System.out.println(tasks);
    }

    public String getTaskList(TaskList tasks) {
        assert tasks != null : "getTaskList tasks is null";
        return tasks.toString();
    }

    /**
     * Sends Duke's formatted tasks that contain the keyword.
     * @param tasks  TaskList to be queried and formatted.
     * @param keyword  Keyword to be queried.
     */
    public void findTasks(TaskList tasks, String keyword) {
        assert tasks != null : "findTasks tasks is null";
        System.out.println(this.foundMessage);
        System.out.println(tasks.findTasks(keyword));
    }

    public String getFoundTasks(TaskList tasks, String keyword) {
        assert tasks != null : "getFoundTasks tasks is null";
        return this.foundMessage + "\n"
                + tasks.findTasks(keyword);
    }

    /**
     * Sends Duke's message of the current number of tasks in the TaskList.
     * @param tasks  TaskLists with tasks to be counted.
     */
    public void sendCount(TaskList tasks) {
        assert tasks != null : "sendCount tasks is null";
        int count = tasks.getCount();
        System.out.println("Now you have " + count + (count == 1 ? " task " : " tasks ") + "in the list.");
    }

    public String getCountMessage(TaskList tasks) {
        assert tasks != null : "getCountMessage tasks is null";
        int count = tasks.getCount();
        return "Now you have " + count + (count == 1 ? " task " : " tasks ") + "in the list.";
    }

    public void sendUndoFailMessage() {
        System.out.println(this.undoFailMessage);
    }

    public String getUndoFailMessage() {
        return this.undoFailMessage;
    }

    public void sendUndoSuccessMessage() {
        System.out.println(this.undoSuccessMessage);
    }

    public String getUndoSuccessMessage() {
        return this.undoSuccessMessage;
    }
}
