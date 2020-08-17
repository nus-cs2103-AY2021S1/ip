import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final List<Task> storage = new ArrayList<>();
    private static final String border = "____________________________________________________________\n";

    public static boolean checkBye(Task task) {
        if(task.getName().equals("bye")) {
            System.out.println(border + "Bye. Hope to see you again soon!\n" + border);
            return true;
        }
        return false;
    }

    public static void displayList() {
        int listLen = storage.size();
        System.out.println(border.replace("\n", ""));
        for(int i = 1; i <= listLen; i++) {
            Task curr = storage.get(i - 1);
            String tickCross = "";

            if (curr.isDone()) {
                tickCross = "[✓] ";
            } else {
                tickCross = "[✗] ";
            }

            System.out.println(i + "."+ tickCross + curr.getName());
        }
        System.out.println(border);
    }

    public static void addTask(Task task) {
        storage.add(task);
        System.out.println(border + "added: " + task.getName() + "\n" + border);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(border + "Hello! I'm Duke\n" + "What can I do for you?\n" + border);

        while(scan.hasNext()) {
            Task input = new Task(scan.nextLine());
            if(checkBye(input)) {
                return;
            } else if(input.getName().equals("list")) {
                displayList();
            } else {
                addTask(input);
            }
        }
    }
}
