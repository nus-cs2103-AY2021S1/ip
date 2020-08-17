import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        readInputs();
    }

    private static void greet() {
        String logo =
                " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print("Hello, I am\n" + logo);
        System.out.println("___________________________________________________" +
            "\nDuke: What can i do for you?");
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + ". " + task);
        }
        System.out.println("--------------------------" +
                "-------------------------");
    }

    private static void printTotalNumberOfTasks() {
        int numTasks = tasks.size();
        if (numTasks < 2) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
    }

    private static void setDoneTask(int index) {
        Task task = tasks.get(index - 1); // index - 1 to match the index in ArrayList
        task.markDone();
        System.out.println("Nice! I've marked this task as done: " +
                task +
                "\n---------------------------------------------------");
    }

    private static void handleTask(String input) {
        String taskType = input.split(" ")[0];
        if (taskType.equals("done")) {
            String taskIndex = input.split(" ")[1];
            setDoneTask(Integer.parseInt(taskIndex));
        } else {
            String taskName = "";
            if (taskType.equals("todo")){
                taskName = input.substring(5);
                Task taskCreated = new Todo(taskName);
                tasks.add(taskCreated);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + taskCreated);
                printTotalNumberOfTasks();
            } else if (taskType.equals("deadline")) {
                String[] task = input.substring(9).split(" /by ");
                taskName = task[0];
                String timeBy = task[1];
                Task taskCreated = new Deadline(taskName, timeBy);
                tasks.add(taskCreated);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + taskCreated);
                printTotalNumberOfTasks();
            } else if (taskType.equals("event")) {
                String[] task = input.substring(6).split(" /at ");
                taskName = task[0];
                String timeAt = task[1];
                Task taskCreated = new Event(taskName, timeAt);
                tasks.add(taskCreated);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + taskCreated);
                printTotalNumberOfTasks();
            } else { }
            System.out.println("___________________________________________________");
        }
    }

    private static void readInputs() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (input.equals("list")) {
                printTasks();
            } else {
               handleTask(input);
            }

        }
    }
}
