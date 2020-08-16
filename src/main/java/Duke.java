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
        System.out.println();
    }

    private static void displayTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task t : tasks) {
            System.out.println(i + "." + t.toString());
            i++;
        }
    }

    private static void addTask(String str, ArrayList<Task> tasks) {
        if (str.contains(" ")) {
            String[] arr = str.split(" ", 2);
            String str2 = arr[1];
            switch (arr[0]) {
                case "todo":
                    ToDo td = new ToDo(str2);
                    insert(td, tasks);
                    break;
                case "deadline":
                    if (str2.contains("/by ")) {
                        String[] arr2 = str2.split("/by ", 2);
                        Deadline dl = new Deadline(arr2[0], arr2[1]);
                        insert(dl, tasks);
                    } else {
                        System.out.println("Please indicate a deadline with /by");
                    }
                    break;
                case "event":
                    if (str2.contains("/at ")) {
                        String[] arr2 = str2.split("/at ", 2);
                        Event ev = new Event(arr2[0], arr2[1]);
                        insert(ev, tasks);
                    } else {
                        System.out.println("Please indicate a time with /at");
                    }
                    break;
                default:
                    System.out.println("Error, please enter a valid command.");
            }
        } else {
            System.out.println("Error, please enter a valid command.");
        }
    }

    public static void insert(Task T, ArrayList<Task> tasks) {
        tasks.add(T);
        System.out.println("Task has been added:");
        System.out.println(T.toString());
        System.out.println("You now have " + tasks.size() + " tasks in the list");
    }

    private static void completeTask(String str, ArrayList<Task> tasks) {
        String val = str.substring(5);
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
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
