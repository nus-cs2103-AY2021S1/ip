import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> list = new ArrayList<>();

    public static void Greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void Exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void Echo() {

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                ListOut();
            } else if (input.contains("done")) {
                String[] arr = input.split(" ");
                int index = Integer.parseInt(arr[1]);

                Done(index);
            } else {
                String taskType = input.substring(0, input.indexOf(" "));
                String taskName = input.substring(input.indexOf(" ") + 1);

                Task task;

                if (taskType.equals("todo")) {
                    task = new ToDo(taskName);
                } else {
                    System.out.println("debug");
                    task = new Task(taskName);
                }

                Add(task);
                System.out.println();
            }
        }
    }

    public static void Add(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        list.add(task);
        System.out.printf("     Now you have %d tasks in the list.\n", list.size());
        System.out.println("    ____________________________________________________________");
    }

    public static void ListOut() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("     %d. %s \n", i + 1, list.get(i));
        }

        System.out.println("    ____________________________________________________________");
    }

    public static void Done(int index) {
        Task task = list.get(index - 1);
        task.completed();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");

        System.out.println("       " + task);
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void main(String[] args) {

        Greet();

        Echo();

        Exit();
    }
}
