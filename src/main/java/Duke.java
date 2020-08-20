import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Greet();

        ArrayList<Task> todo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Task task = new Task(sc.nextLine());

       while (!task.getDescription().equals("bye")) {
           add(task);
           todo.add(task);
           task = new Task(sc.nextLine());
           if (task.getDescription().equals("list")) {
                printList(todo);
                task = new Task(sc.nextLine());
           }
       }
       Bye();

    }
    public static void Greet() {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printList(ArrayList<Task> todo) {
        int taskNum = 0;
        System.out.println("    ____________________________________________________________");
        for (Task task : todo) {
            taskNum++;
            System.out.println("     " + taskNum + ".[" + task.getStatusIcon() + "] " + task.getDescription());
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void add(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + task.getDescription());
        System.out.println("    ____________________________________________________________");
    }

    public static void Bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
