package duke.ui;

import duke.exception.DukeException;
import duke.task.*;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
      this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("#############################################################");
    }

    public void showError(DukeException e) {
        System.out.println(e.toString());
    }

    public void showLoadingError() {
        System.out.println(
                "███████╗██████╗░██████╗░░█████╗░██████╗░  ░█████╗░████████╗  ██╗░░░░░░█████╗░░█████╗░██████╗░\n" +
                "██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔══██╗  ██╔══██╗╚══██╔══╝  ██║░░░░░██╔══██╗██╔══██╗██╔══██╗\n" +
                "█████╗░░██████╔╝██████╔╝██║░░██║██████╔╝  ███████║░░░██║░░░  ██║░░░░░██║░░██║███████║██║░░██║\n" +
                "██╔══╝░░██╔══██╗██╔══██╗██║░░██║██╔══██╗  ██╔══██║░░░██║░░░  ██║░░░░░██║░░██║██╔══██║██║░░██║\n" +
                "███████╗██║░░██║██║░░██║╚█████╔╝██║░░██║  ██║░░██║░░░██║░░░  ███████╗╚█████╔╝██║░░██║██████╔╝\n" +
                "╚══════╝╚═╝░░╚═╝╚═╝░░╚═╝░╚════╝░╚═╝░░╚═╝  ╚═╝░░╚═╝░░░╚═╝░░░  ╚══════╝░╚════╝░╚═╝░░╚═╝╚═════╝░");
    }

    public void greetingMessage() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo +
        "____________________________________________________________\n" +
        " Hello! I'm Duke\n" +
        " What can I do for you?\n" +
                "____________________________________________________________");
    }

    public void addTaskMessage(Task t, TaskList tasks) {
        String message =
                "Got it. I've added this task:\n" +
                "  " + t.toString() + "\n" +
                "Now you have " + tasks.getNumOfTasks() + " tasks in the list.";
        System.out.println(message);
    }

    public void doneTaskMessage(Task t) {
        String message =
        "Nice! I've marked this task as done:\n" +
        "  " + t.toString();
        System.out.println(message);
    }

    public void deleteTaskMessage(Task t, TaskList tasks) {
        String message =
                "Noted. I've removed this task:\n" +
                        "  " + t.toString() + "\n" +
                        "Now you have " + tasks.getNumOfTasks() + " tasks in the list.";
        System.out.println(message);
    }

    public void listTasksMessage(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.getNumOfTasks(); i++) {
            Task t = tasks.retrieve(i);
            System.out.format("%d.%s\n", i, t.toString());
        }
    }

    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void findTasksMessage(TaskList tasks, String keyWords) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.getNumOfTasks(); i++) {
            Task t = tasks.retrieve(i);
            if (t.contains(keyWords)) {
                System.out.format("%d.%s\n", i, t.toString());
            }
        }
    }
}
