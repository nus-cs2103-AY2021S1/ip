import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    // Create a Scanner object
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        List<Task> tasks = new ArrayList<>();
        Task task = new Task(sc.nextLine());
        while (!(task.toString().equals("bye"))) {
            if (task.toString().equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ".[" + tasks.get(i - 1).getStatusIcon() + "] " + tasks.get(i - 1));
                }
                task = new Task(sc.nextLine());
            } else if (task.toString().contains("done")) {
                String string = task.toString();
                int index = Integer.parseInt(string.replaceAll("\\D", ""));
                tasks.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  [" + tasks.get(index - 1).getStatusIcon() + "] " + tasks.get(index - 1));
                task = new Task(sc.nextLine());
            } else {
                tasks.add(task);
                System.out.println("added: " + task);
                task = new Task(sc.nextLine());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}