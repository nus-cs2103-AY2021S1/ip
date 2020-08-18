import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello, I'm Duke");
        System.out.println("How to add tasks to the list:");
        System.out.println("ToDo - type 'todo' followed by the description");
        System.out.println("Deadline - type 'deadline' followed by the description," +
                "then '/by', then due date");
        System.out.println("Event - type 'event' followed by the description, " +
                "then '/at', then timing");
        System.out.println("Type 'done' followed by the task number " +
                "and I'll mark it as done");
        System.out.println("Type 'list' to see the list");
        System.out.println("Type 'bye' to exit");
        String next;
        while (true) {
            next = sc.nextLine();
            if (next.equalsIgnoreCase("bye")) {
                System.out.println("Bye! :>");
                sc.close();
                System.exit(0);
            } else if (next.equalsIgnoreCase("list")) {
                System.out.println("Items in list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).toString());
                }
            } else if (next.length() >= 4 &&
                    next.substring(0, 4).equalsIgnoreCase("done")) {
                int taskNo = Integer.parseInt(next.substring(5));
                Task completedTask = list.get(taskNo - 1);
                completedTask.markAsDone();
                System.out.println("Task marked complete:");
                System.out.println(completedTask.toString());
            } else {
                Task newTask = next.substring(0, 4).equalsIgnoreCase("todo")
                        ? new ToDo(next.substring(5))
                        : next.substring(0, 5).equalsIgnoreCase("event")
                        ? new Event(next.substring(6))
                        : new Deadline(next.substring(9));
                list.add(newTask);
                System.out.println("added: " + newTask.toString());
                System.out.println("Total tasks: " + list.size());
            }
        }

    }
}
