import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Task task = new Task(sc.nextLine());

        while (!(task.getDescription().equals("bye"))) {
            if (task.getDescription().equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                task = new Task(sc.nextLine());
            } else if (task.getDescription().contains("done")) {
                String string = task.getDescription();
                int index = Integer.parseInt(string.replaceAll("\\D",""));
                tasks.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n " + tasks.get(index - 1));
                task = new Task(sc.nextLine());
            } else {
                if (task.getDescription().contains("todo")) {
                    String string = task.getDescription().replaceFirst("todo","").strip();
                    task = new ToDos(string);
                    tasks.add(task);
                } else if (task.getDescription().contains("deadline")) {
                    String string = task.getDescription().replaceFirst("deadline","");
                    String[] strings = string.split("/by");
                    task = new Deadlines(strings[0].strip(), strings[1].strip());
                    tasks.add(task);
                } else if (task.getDescription().contains("event")) {
                    String string = task.getDescription().replaceFirst("event","");
                    String[] strings = string.split("/at");
                    task = new Events(strings[0].strip(), strings[1].strip());
                    tasks.add(task);
                }
                System.out.println("Got it. I've added this task:\n " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                task = new Task(sc.nextLine());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}