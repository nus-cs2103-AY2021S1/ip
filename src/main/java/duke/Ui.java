package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {

    Scanner sc;
    Ui() {
        sc = new Scanner(System.in);
    }

    void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i ++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    public void printSearchResults(List<Task> tasks) {
        if (tasks.size() <= 0) {
            System.out.println("Sorry, there are no matching tasks with that keyword");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i - 1));
            }
        }
    }

    public void printReply(String message) {
        System.out.println(message);
    }

    String readCommand() {
       return sc.nextLine();
    };
}
