package duke;

import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public void showLoadingError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void greeting() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public void goodbye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public void list(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTasks().get(i).recordString());
        }
    }

    public void done(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.recordString());
    }

    public void delete(int index, Task t, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.recordString());
        System.out.println("Now, you have " +  size + " tasks in the list");
    }

    public void add(Task t, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.recordString());
        System.out.println("Now, you have " + size + " tasks in the list");
    }

    public void initializeDukeUI(TaskList tasks) {
        greeting();
        try {
            Scanner userInput = new Scanner(System.in);
            while (userInput.hasNext()) {
                String input = userInput.nextLine();
                Parser.parse(input, tasks);
            }
        } catch (DukeException e) {
            showLoadingError(e);
        }
    }
}
