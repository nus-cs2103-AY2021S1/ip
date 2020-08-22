package duke.ui;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static String LINE = "\t" + "_".repeat(75);
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    private void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showGreeting() {
        showWelcome();
        showLine();
        System.out.println("\t Hello! I'm Duke\n"
                + "\t What can I do for you?");
        showLine();
    }

    public void showBye() {
        showLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        showLine();
    }

    public void showMessages(ArrayList<String> strings) {
        showLine();
        strings.forEach(str -> System.out.println(addIndent(str)));
        showLine();
    }

    public void showResult(String response, Task task, String tasksLeft) {
        showLine();
        System.out.println(addIndent(response));
        System.out.println(addIndent(addIndent(task.toString())));
        System.out.println(addIndent(tasksLeft));
        showLine();
    }

    public void show(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    private String addIndent(String str) {
        return String.format("\t %s", str);
    }

    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            // todo: throw no input found exception
            return "bye";
        }
    }

}