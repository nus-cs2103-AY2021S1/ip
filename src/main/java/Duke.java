import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int numOfTasks = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        start();
    }

    public static void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println(wrapWithLines(greet()));

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(wrapWithLines(exit()));
                System.exit(0);
            } else if (input.equals("list")) {
                System.out.println(wrapWithLines(list()));
            } else if (input.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                System.out.println(wrapWithLines(done(index)));
            } else {
                System.out.println(wrapWithLines(addTask(input)));
            }
        }
    }

    private static String greet() {
        return "\t Hello! I'm Duke\n"
                + "\t What can I do for you?";
    }

    private static String echo(String str) {
        return "\t " + str;
    }

    private static String exit() {
        return "\t Bye. Hope to see you again soon!";
    }

    private static String line() {
        return "\t" + "_".repeat(50);
    }

    private static String wrapWithLines(String str) {
        return line() + "\n" + str + "\n" + line();
    }

    private static String list() {
        StringBuilder str = new StringBuilder("\t Here are the tasks in your list:\n");
        for (int i = 0; i < numOfTasks; i++) {
            str.append("\t ").append(i + 1).append(".")
                    .append(tasks[i].toString());
            if (i != numOfTasks - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }

    private static String done(int input) {
        int index = input - 1;
        Task task = tasks[index];
        task.markAsDone();
        return "\t Nice! I've marked this task as done:\n\t\t"
                + task.toString();
    }

    private static void addTaskToList(Task task) {
        tasks[numOfTasks] = task;
        numOfTasks++;
    }

    private static String addTask(String input) {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String taskString = splitInput[1];
        Task task;
        switch (command) {
            case "todo":
                task = new Todo(taskString);
                break;
            case "deadline": {
                String[] parsed = taskString.split("/by");
                task = new Deadline(parsed[0], parsed[1]);
                break;
            }
            case "event": {
                String[] parsed = taskString.split("/at");
                task = new Event(parsed[0], parsed[1]);
                break;
            }
            default:
                // throw exception
                return "";
        }
        addTaskToList(task);
        return "\t Got it. I've added this task: \n"
                + "\t\t" + task.toString() + "\n"
                + "\t Now you have " + numOfTasks + " tasks in this list.";
    }

}
