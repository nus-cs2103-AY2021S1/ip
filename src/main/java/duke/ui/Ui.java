package duke.ui;

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

    public void show(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "bye";
        }
    }

}