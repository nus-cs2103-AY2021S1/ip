import java.util.Scanner;

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
        System.out.println("Hello from\n" + this.logo);
    }
    public void greet() {
        System.out.println(greeting);
    }
    public void sendBar() {
        System.out.println(bar);
    }
    public void bidFarewell() {
        System.out.println(goodbye);
    }
    public void sendAddTaskMessage(String taskString) {
        System.out.println(add);
        System.out.println("  " + taskString);
    }
    public void sendMarkedAsDoneMessage(String taskString) {
        System.out.println(done);
        System.out.println("  " + taskString);
    }
    public void sendDeleteTaskMessage(String taskString) {
        System.out.println(delete);
        System.out.println("  " + taskString);
    }
    public void sendFailedInitialiseMessage() {
        System.out.println(failedInitialise);
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
    public void sendCount(TaskList tasks) {
        int count = tasks.getCount();
        System.out.println("Now you have " + count + (count==1?" task ":" tasks ") + "in the list.");
    }
}
