package duke;

import java.util.Scanner;

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

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getUserInput() {
        String input = this.sc.nextLine();
        if (input.equals("bye")) {
            this.sc.close();
        }
        return input;
    }
    public void sendInitialiseMessage() {
        System.out.println("Hello from\n" + this.LOGO);
    }
    public void greet() {
        System.out.println(this.GREETING);
    }
    public void sendBar() {
        System.out.println(this.BAR);
    }
    public void bidFarewell() {
        System.out.println(this.GOODBYE);
    }
    public void sendAddTaskMessage(String taskString) {
        System.out.println(this.ADD);
        System.out.println("  " + taskString);
    }
    public void sendMarkedAsDoneMessage(String taskString) {
        System.out.println(this.DONE);
        System.out.println("  " + taskString);
    }
    public void sendDeleteTaskMessage(String taskString) {
        System.out.println(this.DELETE);
        System.out.println("  " + taskString);
    }
    public void sendFailedInitialiseMessage() {
        System.out.println(this.FAILED_INITIALISE);
    }
    public void sendExceptionMessage(Exception e) {
        System.out.println(e);
    }
    public void sendExceptionMessage(String message) {
        System.out.println(message);
    }
    public void listTasks(TaskList tasks) {
        System.out.println(tasks);
    }
    public void findTasks(TaskList tasks, String keyword) {
        System.out.println(this.FOUND_MESSAGE);
        System.out.println(tasks.findTasks(keyword));
    }
    public void sendCount(TaskList tasks) {
        int count = tasks.getCount();
        System.out.println("Now you have " + count + (count==1?" task ":" tasks ") + "in the list.");
    }
}
