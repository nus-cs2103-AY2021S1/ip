package duke;

import java.util.Scanner;

import duke.resource.TaskList;
import duke.task.Task;
import duke.util.DukeException;

public class Ui {

    private final Scanner sc;

    private static final String LINE =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void loaded(TaskList tasks) {
        System.out.println(
                "    A save file has been found and loaded!\n" +
                "    Your current tasks are: ");
        int i = 0;
        if (tasks.size() == 0) {
            System.out.println("    ... empty! Good work!");
        } else {
            while (tasks.size() > i) {
                System.out.println("        " + ++i + ". " + tasks.getTask(i).toString());
            }
        }
        System.out.println(LINE);
    }

    public void printAdd(TaskList tasks, Task task) {
        System.out.println(
                "    Got it. I've added this task:\n        " +
                task.toString() +
                "\n    You now have " + tasks.size() +
                (tasks.size() == 1 ? " task" : " tasks") +
                " in your list.\n" +
                LINE);
    }

    public void printBye() {
        System.out.println(
                "    Bye. Hope to see you again soon!\n" +
                LINE);
    }

    public void printDone(Task task) {
        System.out.println(
                "    Nice! I've marked this task as done:\n        " +
                task.toString() + "\n" +
                LINE);
    }

    public void printDelete(TaskList tasks, Task task) {
        System.out.println(
                "    Noted. I've removed this task:\n        " +
                task.toString() +
                "\n    You now have " + tasks.size() +
                (tasks.size() == 1 ? " task" : " tasks") +
                " in your list.\n" +
                LINE);
    }

    public void printError(DukeException e) {
        System.out.println(
                e.getMessage() + "\n" +
                LINE);
    }

    public void printList(TaskList list) {
        int i = 0;
        System.out.println("    Here are the tasks in your list: ");
        while (list.size() > i) {
            System.out.println("        " + ++i + ". " + list.getTask(i).toString());
        }
        System.out.println(LINE);
    }

    public String read() {
        return sc.nextLine();
    }

    public void start() {
        System.out.println(
                "    Awaiting input...\n" +
                LINE);
    }

    public void welcome() {
        String logo
                = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE + "\n" + logo + "\n    Hello! I'm Duke!\n");
    }

}
