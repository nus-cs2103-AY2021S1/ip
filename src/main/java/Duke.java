import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm duke. What can I do for you? \n" + logo);

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            line();
            if (str.equals("list")) {
                displayTasks(tasks);
            } else {
                if (str.startsWith("done ")) {
                    completeTask(str, tasks);
                } else {
                    addTask(str, tasks);
                }
            }
            line();
            str = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    public static void line() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2500");
        }
        System.out.println("");
    }

    private static void displayTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task t: tasks) {
            System.out.println(i + "." + t.toString());
            i++;
        }
    }

    private static void addTask(String str, ArrayList<Task> tasks) {
        Task t = new Task(str);
        tasks.add(t);
        System.out.println("added: " + t.description);
    }

    private static void completeTask(String str, ArrayList<Task> tasks) {
        String val = str.substring(5);
        System.out.println(val);
        if (isInteger(val)) {
            int i = Integer.parseInt(val);
            if (i > 0 && i <= tasks.size()) {
                tasks.get(i - 1).complete();
            } else {
                System.out.println("Error, please key in a valid number.");
            }
        } else {
            System.out.println("Error, please key in a valid number.");
        }
    }

    public static boolean isInteger(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
