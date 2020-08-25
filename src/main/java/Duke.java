import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Greet();

        ArrayList<Task> TaskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

       while (!input.equals("bye")) {

           String errorMessage = DukeExceptionHandler.handleException(input);
           if (errorMessage != null) {
               System.out.println(errorMessage);
           }
           else {
               if (!input.contains("done") && !input.contains("list")) {
                   if (input.contains("todo")) {
                       String[] textArray = input.split(" ", 2);
                       Todo todo = new Todo(textArray[1]);
                       TaskList.add(todo);
                       add(todo, TaskList);
                   }
                   if (input.contains("deadline")) {
                       String[] textArray = input.split(" ", 2);
                       Deadline deadline = new Deadline(textArray[1]);
                       TaskList.add(deadline);
                       add(deadline, TaskList);
                   }
                   if (input.contains("event")) {
                       String[] textArray = input.split(" ", 2);
                       Event event = new Event(textArray[1]);
                       TaskList.add(event);
                       add(event, TaskList);
                   }
               } else if (input.equals("list")) {
                   printList(TaskList);
               } else if (input.contains("done")) {
                   String[] textArray = input.split(" ", 2);
                   int taskNum = Integer.parseInt(textArray[1]);
                   Task doneTask = TaskList.get(taskNum - 1);
                   doneTask.markAsDone();
                   System.out.println("Nice! I've marked this task as done:\n" + doneTask);
               }
           }
           input = sc.nextLine();
       }
       Bye();

    }
    public static void Greet() {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printList(ArrayList<Task> TaskList) {
        System.out.println("    ____________________________________________________________");
        for (Task task : TaskList) {
            System.out.println("     " + (TaskList.indexOf(task) + 1) + "." + task);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void add(Task task, ArrayList<Task> TaskList) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:\n" + "       " + task);
        System.out.println("     Now you have " + TaskList.size() + " tasks in the list");
        System.out.println("    ____________________________________________________________");
    }

    public static void Bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
