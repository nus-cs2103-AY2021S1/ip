import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    // Create a Scanner object
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        List<Task> tasks = new ArrayList<>();
        Task task = new Task(sc.nextLine());
        while (!(task.getDescription().equals("bye"))) {
            if (task.getDescription().equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i - 1));
                }
                task = new Task(sc.nextLine());
            } else if (task.getDescription().contains("done")) {
                String string = task.getDescription();
                int index = Integer.parseInt(string.replaceAll("\\D", ""));
                tasks.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index - 1));
                task = new Task(sc.nextLine());
            } else {
                if (task.getDescription().contains("todo")) {
                    String str = task.getDescription().replaceFirst("todo","").strip();
                    task = new ToDo(str);
                    tasks.add(task);
                } else if (task.getDescription().contains("deadline")) {
                    String str = task.getDescription().replaceFirst("deadline","");
                    String[] strings = str.split("/by");
                    task = new Deadline(strings[0].strip(), strings[1].strip());
                    tasks.add(task);
                } else if (task.getDescription().contains("event")) {
                    String str = task.getDescription().replaceFirst("event","");
                    String[] strings = str.split("/at");
                    task = new Event(strings[0].strip(), strings[1].strip());
                    tasks.add(task);
                }
                System.out.println("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                task = new Task(sc.nextLine());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}