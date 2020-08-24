import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Duke {
    private static ArrayList<Task> tasks = TaskManager.readFile();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        acceptCommand(sc);
    }

    private static void greet() {
        String greeting = "Hello! I'm Duke \nWhat can I do for you?";
        printResponse(greeting);
    }

    private static void acceptCommand(Scanner sc) {
        boolean shouldLoop = true;
        while (shouldLoop) {
            try {
                String input = sc.nextLine();
                Command command = parseInput(input);
                switch (command) {
                    case BYE:
                        handleBye();
                        shouldLoop = false;
                        break;
                    case COMMANDS:
                        handleCommands();
                        break;
                    case DEADLINE:
                        handleDeadline(input);
                        break;
                    case DELETE:
                        handleDelete(input);
                        break;
                    case DONE:
                        handleDone(input);
                        break;
                    case EVENT:
                        handleEvent(input);
                        break;
                    case LIST:
                        handleList();
                        break;
                    case TODO:
                        handleToDo(input);
                        break;
                    case UNKNOWN:
                        handleUnknown();
                        break;
                    default:
                        break;
                }
            } catch (DukeException e) {
                printResponse(e.getMessage());
            }
        }
    }

    private static Command parseInput(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("commands")) {
            return Command.COMMANDS;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.matches("delete \\d+")) {
            return Command.DELETE;
        } else if (input.matches("done \\d+")) {
            return Command.DONE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.startsWith("todo")) {
            return Command.TODO;
        } else {
            return Command.UNKNOWN;
        }
    }

    private static void handleBye() {
        printResponse("Bye. Hope to see you again soon!");
    }

    private static void handleCommands() {
        String response = "Here are all the commands:\n\n"
                + "bye\n"
                + "deadline <task> /by <time>\n"
                + "delete <task index>\n"
                + "done <task index>\n"
                + "event <task> /at <time>\n"
                + "list\n"
                + "todo <task>";
        printResponse(response);
    }

    private static void handleDeadline(String command) throws InvalidDeadlineException {
        try {
            String[] input = command.substring(command.indexOf(' ') + 1).split(" /by ");
            String description = input[0];
            String byString = input[1];
            Date by = DateFormatter.extractTimestampInput(byString);
            Task task = new Deadline(description, by);
            tasks.add(task);
            TaskManager.saveFile(tasks);
            String response = String.format(
                    "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                    task, tasks.size()
            );
            printResponse(response);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            throw new InvalidDeadlineException();
        }
    }

    private static void handleDelete(String command) throws InvalidDeleteIndexException {
        int index = Integer.parseInt(command.split(" ")[1]);
        if (index > tasks.size() || index < 1) {
            throw new InvalidDeleteIndexException(tasks.size());
        }

        Task task = tasks.remove(index-1);
        TaskManager.saveFile(tasks);
        String response = String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", task, tasks.size());
        printResponse(response);
    }

    private static void handleDone(String command) throws InvalidDoneIndexException {
        int index = Integer.parseInt(command.split(" ")[1]);
        if (index > tasks.size() || index < 1) {
            throw new InvalidDoneIndexException(tasks.size());
        }

        Task task = tasks.get(index-1);
        task.completeTask();
        TaskManager.saveFile(tasks);
        String response = "Nice! I've marked this task as done:\n  " + task.toString();
        printResponse(response);
    }

    private static void handleEvent(String command) throws InvalidEventException {
        try {
            String[] input = command.substring(command.indexOf(' ') + 1).split(" /at ");
            String description = input[0];
            String atString = input[1];
            Date at = DateFormatter.extractTimestampInput(atString);
            Task task = new Event(description, at);
            tasks.add(task);
            TaskManager.saveFile(tasks);
            String response = String.format(
                    "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                    task, tasks.size()
            );
            printResponse(response);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            throw new InvalidEventException();
        }
    }

    private static void handleList() {
        StringJoiner response = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.add(String.format("%d.%s", i+1, tasks.get(i)));
        }
        printResponse(response.toString());
    }

    private static void handleToDo(String command) throws InvalidToDoException {
        String[] split = command.split(" ");
        if (split.length == 1) {
            throw new InvalidToDoException();
        }

        String[] descriptionArray = Arrays.copyOfRange(split, 1, split.length);
        String description = String.join(" ", descriptionArray);
        Task task = new ToDo(description);
        tasks.add(task);
        TaskManager.saveFile(tasks);
        String response = String.format(
                "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                task, tasks.size()
        );
        printResponse(response);
    }

    private static void handleUnknown() throws DukeException{
        throw new DukeException();
    }

    // Wrapper method for printing with horizontal line borders and 1 tab indent
    private static void printResponse(String string) {
        String horizontalLine = "----------------------------------------------------------------";
        String formatted = String.format("%s\n%s\n%s", horizontalLine, string, horizontalLine)
                .replaceAll("(?m)^", "\t");
        System.out.println(formatted);
    }
}
