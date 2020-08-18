import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String horiLine = "____________________________________________________________" + "\n";
    private static ArrayList<Task> List = new ArrayList<>();

    private static void greet() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(horiLine + greeting + horiLine);
    }

    private static void addTask(Task task) {
        List.add(task);
        System.out.println(horiLine
                + "Got it. I've added this task: " + "\n"
                + task.toString() + "\n"
                + String.format("Now you have %s tasks in the list.", Task.numberOfTasks) + "\n"
                + horiLine);
    }

    private static void echo() {
        greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) { //exit condition
                String goodbye = "Bye. Hope to see you again soon!";
                System.out.println(horiLine + goodbye + "\n" + horiLine);
                break;
            } else if (input.equals("list")) {
                System.out.println(horiLine + "Here are the tasks in your list:");
                for (Task task : List) {
                    //print out task with numbering
                    System.out.println(String.format("%s.", task.getNumber()) + task.toString());
                }
                System.out.println(horiLine);
            } else if(input.startsWith("done")) {
                String[] number = input.split("done ");
                markDone(Integer.parseInt(number[1]));
            } else if(input.startsWith("deadline")) {
                //whatever is after deadline
                String deadlineInput = (input.split("deadline "))[1];
                String deadlineName = (deadlineInput.split(" /by "))[0];
                String deadlineTime = (deadlineInput.split(" /by "))[1];
                Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
                addTask(newDeadline);
            } else if(input.startsWith("todo")) {
                String[] todoInput = input.split("todo ");
                String todoName = todoInput[1];
                Todo newTodo = new Todo(todoName);
                addTask(newTodo);
            } else if(input.startsWith("event")) {
                String eventInput = (input.split("event "))[1];
                String eventName = (eventInput.split(" /at "))[0];
                String eventTime = (eventInput.split(" /at "))[1];
                Event newEvent = new Event(eventName, eventTime);
                addTask(newEvent);
            }
        }
        sc.close();
    }

    private static void markDone(int i) {
        Task task = List.get(i - 1);
        task.setDone();
        System.out.println(horiLine + "Nice! I've marked this task as done:" +  "\n" +
                task.toString() + "\n" + horiLine);
    }

    public static void main(String[] args) {
        echo();
    }
}
