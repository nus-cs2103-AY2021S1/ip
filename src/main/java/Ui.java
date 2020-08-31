import java.util.ArrayList;
import java.io.IOException;

public class Ui {

    public Ui() { }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void add(Task task, ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:\n" + task.toString()
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void done(int n, ArrayList<Task> tasks) {
        System.out.println("Nice! I've marked this task as done: \n" + tasks.get(n-1).toString());
    }

    public static void delete(int n, ArrayList<Task> tasks) {
        System.out.println("Noted. I've removed this task:\n" + tasks.get(n-1).toString());
    }

    public static void count(ArrayList<Task> tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    public static void showCommandError(DukeException e) {
        if (e.getType().equals("EmptyToDo")) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
        if (e.getType().equals("EmptyDeadline")) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (e.getType().equals("EmptyEvent")) {
            System.out.println("OOPS!!! The description of a event cannot be empty.");
        }
        if (e.getType().equals("invalid")) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void showLoadingError() {
        System.out.println("File not found");
    }

    public static void showIOException(IOException e) {
        System.out.println("Oops! " + e.getMessage());
    }
}
