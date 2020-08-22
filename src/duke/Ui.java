package duke;

import exception.NoSuchTaskException;

import task.Task;

import java.util.Scanner;

public class Ui {
    public void say(String text) {
        System.out.println(text);
    }

    public void sayException(Exception e) {
        System.out.println(e);
    }

    public void showLine() {
        System.out.println("------------------------------------------------------------");
    }

    public void hello() {
        say("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    public void bye() {
        say("Bye. Hope to see you again soon!");
    }

    public void sayAddedTask(Task task, int tasksTotal) {
        say("Got it. I've added this task:\n" + task + "\nNow you have " + tasksTotal + " tasks in the list.");
    }

    public void sayDeletedTask(Task task, int tasksTotal) {
        say("Noted. I've removed this task:\n" + task + "\nNow you have " + tasksTotal + " tasks in the list.");
    }

    public void sayMarkedTask(Task task) {
        say( "Nice! I've marked this task as done:\n" + task);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

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
