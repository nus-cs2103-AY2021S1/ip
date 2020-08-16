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
        DukePrint.print(greet());

        while (sc.hasNext()) {
            String input = sc.nextLine().toLowerCase();
            try {
                if (input.strip().equals("bye")) {
                    DukePrint.print(exit());
                    System.exit(0);
                } else {
                    DukePrint.print(runCommand(input.strip()));
                }
            } catch (Exception ex) {
                DukePrint.print("\t " + ex.toString());
            }
        }
    }

    private static String runCommand(String input) {
        String[] arr = input.split(" ", 2);
        switch (arr[0]) {
            case "list":
                return list();
            case "done":
                return done(input);
            case "todo":
            case "deadline":
            case "event":
                return addTask(input);
            default:
                throw new InvalidCommandException();
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

    private static String done(String input) {
        String[] arr = input.split(" ");
        if (arr.length < 2) {
            throw new EmptyTaskDoneException();
        } else {
            try {
                int index = (Integer.parseInt(arr[1])) - 1;
                Task task = tasks[index];
                task.markAsDone();
                return "\t Nice! I've marked this task as done:\n\t\t"
                        + task.toString();
            } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
                throw new InvalidTaskDoneException();
            }
        }

    }

    private static void addTaskToList(Task task) {
        tasks[numOfTasks] = task;
        numOfTasks++;
    }

    private static String addTask(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length < 2) {
            throw new EmptyTaskDescriptionException(splitInput[0]);
        }
        String command = splitInput[0];
        String taskString = splitInput[1];
        Task task;
        switch (command) {
            case "todo":
                task = new Todo(taskString);
                break;
            case "deadline": {
                String[] parsed = taskString.split("/by");
                if (parsed.length < 2) {
                    throw new EmptyDueDateException();
                }
                if (parsed[0].strip().equals("")) {
                    throw new EmptyTaskDescriptionException(command);
                }
                task = new Deadline(parsed[0], parsed[1]);
                break;
            }
            case "event": {
                String[] parsed = taskString.split("/at");
                if (parsed.length < 2) {
                    throw new EmptyEventDateException();
                }
                if (parsed[0].strip().equals("")) {
                    throw new EmptyTaskDescriptionException(command);
                }
                task = new Event(parsed[0], parsed[1]);
                break;
            }
            default:
                return "";
        }
        addTaskToList(task);
        return "\t Got it. I've added this task: \n"
                + "\t\t" + task.toString() + "\n"
                + "\t Now you have " + numOfTasks + " tasks in this list.";
    }

}
