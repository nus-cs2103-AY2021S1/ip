import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final static String INDENT = "\t";
    private final static String horizL = INDENT +
            "____________________________________________________________";
    private final static String logo = INDENT
            + "           ____                   _      _\n"
            + INDENT + "    __    |  _ \\   _   _    ___  | | _  (_)  ___     __\n"
            + INDENT + "___( o)>  | | | | | | | | /  __| | |/ / | | / _ \\  <(o )___ \n"
            + INDENT + "\\ <_. )   | |_| | | | | | | (__  |   <  | | | __/   ( ._> /\n"
            + INDENT + " `---'    |____/  \\___,_|  \\ __| |_|\\_\\ |_| \\___|    `___' \n";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static String getHorizL() {
        return horizL;
    }

    public static void showLine() {
        System.out.println(horizL);
    }

    public static void showIntro() {
        System.out.println(horizL + "\n" + logo + "\n" +
                INDENT + "Quack. Duckie is here to remember your tasks!" );
        showLoading();
    }

    public static void showEnding() {
        System.out.println(INDENT +
                "Quack! Hope to see you again!");
    }

    public static void addTaskReply(Task t1, ArrayList<Task> lst) {
        System.out.println(INDENT + "Quack! Added: " + t1);
        System.out.println(INDENT + "Now you have " + lst.size() + " task(s) in the list.");
    }

    public static void displayListReply(ArrayList<Task> lst) {
        int index = 1;
        System.out.println(INDENT + "Quack! You have these in your list currently: ");
        for (Task task : lst) {
            System.out.println(INDENT + index + ". " + task);
            index++;
        }
    }

    public static void displayNoListReply() {
        System.out.println(INDENT + "Quack. You have no tasks in the list currently");
        System.out.println(horizL);
    }

    public static void checkTaskReply(Task t1) {
        System.out.println(INDENT
                + "Quack! I've marked this task as done: \n"
                + INDENT + t1);
    }

    public static void deleteTaskReply(Task t1) {
        System.out.println(INDENT + "Quack! I've remove this task: \n" +
                INDENT + t1);
    }

    public static void deleteAllReply() {
        System.out.println(INDENT + "Quack! All tasks are cleared in the list!");
    }

    public static void showError(String msg) {
        System.out.println(INDENT + "Oh no! " + msg);
    }

    public String readCommand() {
        String input = sc.nextLine();
        System.out.println(input);
        return input;
    }

    public static void showLoading() {
        System.out.println(INDENT + "Loading tasks...");
        System.out.println(horizL);
    }
}
