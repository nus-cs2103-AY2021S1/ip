import java.lang.reflect.Array;
import java.util.ArrayList;

public class BotResponses {
    private final static String INDENT = "\t";
    private final static String horizL = INDENT +
            "____________________________________________________________";
    private final static String logo = INDENT
            + "           ____                   _      _\n"
            + INDENT + "    __    |  _ \\   _   _    ___  | | _  (_)  ___     __\n"
            + INDENT + "___( o)>  | | | | | | | | /  __| | |/ / | | / _ \\  <(o )___ \n"
            + INDENT + "\\ <_. )   | |_| | | | | | | (__  |   <  | | | __/   ( ._> /\n"
            + INDENT + " `---'    |____/  \\___,_|  \\ __| |_|\\_\\ |_| \\___|    `___' \n";

    public static String getHorizL() {
        return horizL;
    }

    public static void intro() {
        System.out.println(horizL + "\n" + logo + "\n" +
                INDENT + "Quack. Duckie is here to remember your tasks!\n" +
                INDENT + "You can begin by adding tasks!\n" + horizL);
    }

    public static void ending() {
        System.out.println("\n" + INDENT +
                "Quack! Hope to see you again!\n" + horizL);
    }

    public static void addTaskReply(Task t1, ArrayList<Task> lst) {
        System.out.println(horizL);
        System.out.println(INDENT + "Quack! Added: " + t1);
        System.out.println(INDENT + "Now you have " + lst.size() + " task(s) in the list.");
        System.out.println(horizL);
    }

    public static void displayListReply(ArrayList<Task> lst) {
        int index = 1;
        System.out.println(horizL);
        System.out.println(INDENT + "Quack! You have these in your list: ");
        for (Task task : lst) {
            System.out.println(INDENT + index + ". " + task);
            index++;
        }
        System.out.println(horizL);
    }

    public static void checkTaskReply(Task t1) {
        System.out.println(horizL + "\n" + INDENT
                + "Quack! I've marked this task as done: \n"
                + INDENT + t1 + "\n" + horizL);
    }

    public static void deleteTaskReply(Task t1) {
        System.out.println(horizL + "\n" + INDENT + "Quack! I've remove this task: \n" +
                INDENT + t1 + "\n" + horizL);
    }
}
