import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final List<Task> storage = new ArrayList<>();
    private static final String border = "____________________________________________________________\n";

    public static boolean checkBye(Task task) {
        if (task.getName().equals("bye")) {
            System.out.println(border + "Bye. Hope to see you again soon!\n" + border);
            return true;
        }
        return false;
    }

    public static void displayList() {
        int listLen = storage.size();
        System.out.println(border.replace("\n", ""));
        for (int i = 1; i <= listLen; i++) {
            Task curr = storage.get(i - 1);
            String statusIcon = "[" + curr.getStatusIcon() + "] ";
            System.out.println(i + "." + statusIcon + curr.getName());
        }
        System.out.println(border);
    }

    public static void addTask(Task task) {
        storage.add(task);
        System.out.println(border + "added: " + task.getName() + "\n" + border);
    }

    public static void doneTask(int i) {
        Task t = storage.get(i - 1);
        Task completed = t.setDone(true);
        storage.set(i - 1, completed);
        String statusIcon = "[" + completed.getStatusIcon() + "] ";
        System.out.println(
                border + "Nice! I've marked this task as done:\n" + "  "
                        + statusIcon + completed.getName() + "\n" + border
        );
    }

    public static boolean checkDone(Task task) {
        String[] name = task.getName().split(" ");
        return name[0].equals("done") && name[1].matches("\\d+");
    }

    public static int doneTaskId(Task task) {
        String[] name = task.getName().split(" ");
        return Integer.parseInt(name[1]);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(border + "Hello! I'm Duke\n" + "What can I do for you?\n" + border);

        while (scan.hasNext()) {
            Task input = new Task(scan.nextLine());
            if (checkBye(input)) {
                return;
            } else if (input.getName().equals("list")) {
                displayList();
            } else if (checkDone(input)) {
                doneTask(doneTaskId(input));
            } else {
                addTask(input);
            }
        }
    }
}
