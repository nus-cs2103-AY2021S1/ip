import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void display(String s) {
        displayLine();
        System.out.println(s);
        displayLine();
    }

    public static void displayLine() {
        System.out.println("________________________________________");
    }

    public static void displayTasks(ArrayList<Task> tasks) {
        displayLine();
        System.out.println("Here is your current list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
        displayLine();
    }

    public static void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
