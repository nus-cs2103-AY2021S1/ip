package duke.ui;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    public Ui() { }

    public void showWelcome() {
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print("Hello, I am\n" + logo);
        System.out.println("___________________________________________________" +
                "\nDuke: What can i do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printAddSuccess(Task taskAdded) {
        System.out.println("Got it. I've added this task:" +
                "\n\t" + taskAdded);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("___________________________________________________");
    }

}
