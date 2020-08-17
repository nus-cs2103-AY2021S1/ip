import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

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
            String input = sc.nextLine().toLowerCase().strip();
            try {
                if (input.equals("bye")) {
                    DukePrint.print(exit());
                    System.exit(0);
                } else {
                    DukePrint.print(runCommand(input));
                }
            } catch (Exception ex) {
                DukePrint.print("\t " + ex.getMessage());
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
            case "delete":
                return delete(input);
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
        for (int i = 0; i < tasks.size(); i++) {
            str.append("\t ").append(i + 1).append(".")
                    .append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
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
                Task task = tasks.get(index);
                task.markAsDone();
                return "\t Nice! I've marked this task as done:\n\t\t"
                        + task.toString();
            } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
                throw new NoSuchTaskException();
            }
        }

    }

    private static void addTaskToList(Task task) {
        tasks.add(task);
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
                + "\t Now you have " + tasks.size() + " tasks in this list.";
    }

    private static String delete(String input) {
        String[] arr = input.split(" ");
        if (arr.length < 2) {
            throw new EmptyTaskDeletedException();
        } else {
            try {
                int index = (Integer.parseInt(arr[1])) - 1;
                Task task = tasks.get(index);
                tasks.remove(index);
                return String.format("\t Noted. I've removed this task:\n\t\t%s\n\t Now you have %d tasks in the list",
                        task.toString(), tasks.size());
            } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
                throw new NoSuchTaskException();
            }
        }
    }

}
