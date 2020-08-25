package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    protected String logo;
    protected String line;
    protected Scanner sc;

    public Ui() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.line = "____________________________________________________________\n";
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(line
                + logo
                + "\n Hello, I'm Duke \n What can I do for you?\n"
                + line);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String eMsg) {
        System.out.println(line
                + eMsg + "\n"
                + line);
    }

    private void printLine() {
        System.out.print(line);
    }

    public void printList(List<Task> taskList) {
        if (taskList.size() == 0) {
            printLine();
            System.out.println(" Oops, no task YET. Try to add one!");
            printLine();
            return;
        }
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.get(i));
        }
        printLine();
    }

    public void printDone(Task task) {
        System.out.println(line
                + " Nice! I've marked this task as done: "
                + "\n   " + task + "\n"
                + line);
    }

    public void printDelete(Task task, int size) {
        System.out.println(line
                + " Noted. I've removed this task: "
                + "\n   " + task
                + "\n Now you have " + size + " tasks in the list.\n"
                + line);
    }

    public void printAdd(Task task, int size) {
        System.out.println(line
                + " Got it. I've added this task: ");
        System.out.println("   " + task
                + "\n Now you have " + size + " tasks in the list.\n"
                + line);
    }

    public void printFind(List<Task> findResult) {
        if (findResult.size() == 0) {
            printLine();
            System.out.println(" No task founded. Try to add one!");
            printLine();
            return;
        }
        printLine();
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < findResult.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + findResult.get(i));
        }
        printLine();
    }

    public void bye() {
        System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
    }
}
