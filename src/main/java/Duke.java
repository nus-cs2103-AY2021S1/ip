import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String border = "-----------------------------------------------------";
    public static String indentation = "    ";
    public static List<Task> tasks = new ArrayList<>();

    public static void interact() {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                exit();
                break;
            } else if (line.equals("list")) {
                list();
            } else {
                String[] arrOfStr = line.split(" ", 0);
                if (arrOfStr[0].equals("done")) {
                    int index = Integer.parseInt(arrOfStr[1]) - 1;
                    markDone(index);
                } else {
                    add(line);
                }
            }
        }
    }

    public static void printBorder() {
        System.out.println(indentation + border);
    }

    public static void greet() {
        printBorder();
        System.out.println(indentation + "Hello! I'm Duke\n    What can I do for you?");
        printBorder();
    }

    public static void exit() {
        printBorder();
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        printBorder();
    }

    public static void list() {
        printBorder();
        System.out.println(indentation + "Here are the tasks in your list:");
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(indentation + index + ".[" + task.getStatusIcon() + "] " + task.description);
        }
        printBorder();
    }

    public static void add(String line) {
        printBorder();
        tasks.add(new Task(line));
        System.out.println(indentation + "added: " + line);
        printBorder();
    }

    public static void markDone(int index) {
        Task task = tasks.get(index);
        Task newTask = task.markAsDone();
        tasks.set(index, newTask);
        printBorder();
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + indentation + "[" + newTask.getStatusIcon() + "] " + newTask.description);
        printBorder();
    }

    public static void main(String[] args) {
        interact();
    }
}
