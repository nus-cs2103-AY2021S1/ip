import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final List<Task> storage = new ArrayList<>();
    private static final String border = "____________________________________________________________\n";

    public static void exitLine() {
        System.out.println(border + "Bye. Hope to see you again soon!\n" + border);
    }

    public static void displayList() {
        int listLen = storage.size();
        System.out.println(border.replace("\n", ""));
        for (int i = 1; i <= listLen; i++) {
            Task curr = storage.get(i - 1);
            System.out.println(i + "." + curr);
        }
        System.out.println(border);
    }

    public static void addTask(String s, String next) {
        Task task = new Task(s + " " + next);
        storage.add(task);
        System.out.println(border + "added: " + task.getName() + "\n" + border);
    }

    public static boolean checkDone(String s) {
        return s.equals("done");
    }

    public static void doneTask(String s) {
        if (s.equals("")) {
            addTask("done", "");
            return;
        }

        int i = Integer.parseInt(s);
        if (i < 1 || i > storage.size()) {
            System.out.println(
                    border + "You have entered an invalid number: " + i
                        + ". Please try again.\n" + border
            );
        } else {
            Task t = storage.get(i - 1);
            Task completed = t.setDone(true);
            storage.set(i - 1, completed);
            System.out.println(
                    border + "Nice! I've marked this task as done:\n" + "  "
                            + completed + "\n" + border
            );
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(border + "Hello! I'm Duke\n" + "What can I do for you?\n" + border);

        while (true) {
            String test = scan.next();
            if (test.equals("bye")) {
                exitLine();
                break;
            } else {
                String next = scan.nextLine().replaceFirst(" ", "");
                if (test.equals("list")) {
                    displayList();
                } else if (checkDone(test)) {
                    System.out.println(next);
                    doneTask(next);
                } else {
                    addTask(test, next);
                }
            }
        }
    }
}
