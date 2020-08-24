package duke;

import duke.exception.DukeException;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("------------");
    }

    public void showBye() {
        System.out.println("Bye!");
    }

    public String getLine() {
        return sc.nextLine();
    }

    public void printMessage(String str) {
        System.out.println(str);
    }

    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void printError(DukeException ex) {
        System.out.println(ex);
    }
}
