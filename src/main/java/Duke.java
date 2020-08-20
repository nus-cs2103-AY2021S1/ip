import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Greet();

        ArrayList<Task> todo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

       while (!input.equals("bye")) {
           if (!input.contains("done") && !input.equals("list")) {
               add(new Task(input));
               todo.add(new Task(input));
           }
           if (input.equals("list")) {
                   printList(todo);
           }
           if (input.contains("done")) {
               String[] textArray = input.split(" ", 2);
               int taskNum = Integer.parseInt(textArray[1]);
               Task doneTask = todo.get(taskNum - 1);
               doneTask.markAsDone();
               System.out.println("Nice! I've marked this task as done:\n" + doneTask.getStatusIcon() + " " + doneTask.description);
           }
           input = sc.nextLine();
       }
       Bye();

    }
    public static void Greet() {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printList(ArrayList<Task> todo) {
        System.out.println("    ____________________________________________________________");
        for (Task task : todo) {
            System.out.println("     " + (todo.indexOf(task) + 1) + ".[" + task.getStatusIcon() + "] " + task.getDescription());
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
