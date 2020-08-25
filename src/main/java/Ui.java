import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showFarewell() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showError(String mesg) {
        showLine();
        System.out.println(mesg);
        showLine();
    }

    public void showDone(Task t) {
        showLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("    " + t);
        showLine();
    }

    public void showAdd(Task t, int i) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + t);
        System.out.println("Now you have " + i + " tasks in the list.");
        showLine();
    }

    public void showDelete(Task t, int i) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + t);
        System.out.println("Now you have " + i + " tasks in the list.");
        showLine();
    }

    public void showList(ArrayList<String> lst) {
        showLine();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + "." + lst.get(i));
        }
        showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
