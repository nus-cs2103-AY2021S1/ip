package duke;

import exceptions.DukeException;
import tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private final String ln = "----------------------------------------------------------------";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String takeInput() {
        return sc.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    public void printException(DukeException e) {
        System.out.println(e.getMessage());
    }
    public void printListSize(int size) {
        System.out.println("You now have " + size + (size == 1
                ? " task in your list."
                : " tasks in your list."));
    }
    public void printList(TaskList tasks) {
        System.out.println("Here are your tasks:");
        System.out.println(ln);
        System.out.println(tasks);
        System.out.println(ln);
    }
    public void printExitMessage() {
        System.out.println("Goodbye!");
    }
    public void printAddTask(String task) {
        System.out.println("Added new task: " + task);
    }
    public void printDoneTask() {
        System.out.println("Congrats, I've marked this task as finished!");
    }
    public void printDelTask(TaskList tasks, int idx) {
        System.out.println("The task " + tasks.getTasks().get(idx - 1) + " has been removed.");
    }
    public void printFindTask(String str, int num) {
        if (num == 0) {
            System.out.println("I couldn't find any tasks matching your keyword.");
        } else {
            System.out.println("I found " + num + (num > 1 ? " tasks " : " task ") + "matching your keyword.\n" + str);
        }
    }
}
