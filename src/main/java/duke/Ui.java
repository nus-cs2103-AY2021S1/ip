package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void showGreeting() {
        String message = "Eh what's up\n"
                + "What do you want?";
        System.out.println(wrapMessage(message));
    }

    public void showGoodbye() {
        String message = "Alright I'll see you around!";
        System.out.println(wrapMessage(message));
    }

    public void showLoadingError() {
        System.out.println(wrapMessage("Error loading file!"));
    }

    public void showList(TaskList tasks) {
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            message += (i + 1)
                    + ". "
                    + task
                    +"\n";
        }
        System.out.println(wrapMessage(message));
    }

    public void showAdd(Task task, TaskList tasks) {
        String message = "Got it. I've added this task:\n"
                + task
                + "\n"
                + "Now you have "
                + tasks.getSize()
                + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    public void showDelete(Task task, TaskList tasks) {
        String message = "Noted! I've removed this task:\n"
                + task
                + "\n"
                + "Now you have "
                + tasks.getSize()
                + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    public void showDoneTask(Task task) {
        String message = "Nice! I've marked this task as done:\n"
                + task;
        System.out.println(wrapMessage(message));
    }

    public void showError(String message) {
        System.out.println(wrapMessage(message));
    }

    public String wrapMessage(String message) {
        if (message.endsWith("\n")) {
            // If the message ends with a newline, remove the newline
            message = message.substring(0, message.length() - 1);
        }

        String line = "____________________________________________________________\n";
        return line
                + message
                + "\n"
                + line;
    }
}
