import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final static List<Task> tasks = new ArrayList<>();

    public static void printGreeting() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printPrompt(LOGO
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
        );
    }

    private static String createPrompt(String promptText) {
        String HORIZONTAL_LINE = "____________________________________________________________";
        String INDENTATION = "    ";
        String[] lines = promptText.split("[\\r\\n]+");
        StringBuilder output = new StringBuilder(INDENTATION + HORIZONTAL_LINE + '\n');

        for (String line : lines) {
            output.append(INDENTATION).append(line).append('\n');
        }

        output.append(INDENTATION).append(HORIZONTAL_LINE).append('\n');

        return output.toString();
    }

    public static void printPrompt(String promptText) {
        System.out.println(createPrompt(promptText));
    }

    public static void printAddTask(Task task) {
        printPrompt("Got it. I've added this task:\n  "
                + task + "\n" + "Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
    }

    public static LocalDateTime parseDateTime(String input) throws DukeException {
        String[] params = input.split("\\s");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M[/yyyy][/yy]");

        try {
            LocalDate date;
            LocalTime time = LocalTime.MIDNIGHT;
            TemporalAccessor result = dateFormatter.parseBest(params[0], LocalDate::from, MonthDay::from);

            if (result instanceof LocalDate) {
                date = ((LocalDate) result);
            } else {
                date = ((MonthDay) result).atYear(Year.now().getValue());
            }

            if (params.length == 2) {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
                time = timeFormatter.parse(params[1], LocalTime::from);
            } else if (params.length == 3) {
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:m a");
                time = timeFormatter.parse(params[1] + " " + params[2], LocalTime::from);
            }

            return LocalDateTime.of(date, time);
        } catch(DateTimeParseException e) {
            throw new DukeException("Unable to parse date/time.\n \n"
                    + "Please input your date/time in one of the following formats:\n"
                    + "26/08\n" + "26/08 1:19\n" + "26/08 1:19 AM\n"
                    + "26/08/20\n" + "26/08/20 1:19\n" + "26/08/20 1:19 AM\n"
                    + "26/08/2020\n" + "26/08/2020 1:19\n" + "26/08/2020 1:19 AM");
        }
    }

    public static void addTodo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(description);

        tasks.add(todo);
        printAddTask(todo);
    }

    public static void addDeadline(String input) throws DukeException {
        int index = input.indexOf(" /by ");
        String description = index == -1 ? input : input.substring(0, index);
        String byStr = index == -1 ? "" : input.substring(index + 5);

        if (description.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (byStr.isEmpty()) {
            throw new DukeException("The date/time of an event cannot be empty.");
        }

        LocalDateTime by = parseDateTime(byStr);
        Deadline deadline = new Deadline(description, by);

        tasks.add(deadline);
        printAddTask(deadline);
    }

    public static void addEvent(String input) throws DukeException {
        int index = input.indexOf(" /at ");
        String description = index == -1 ? input : input.substring(0, index);
        String atStr = index == -1 ? "" : input.substring(index + 5);

        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (atStr.isEmpty()) {
            throw new DukeException("The date/time of an event cannot be empty.");
        }

        LocalDateTime at = parseDateTime(atStr);
        Event event = new Event(description, at);

        tasks.add(event);
        printAddTask(event);
    }

    public static void doTask(String otherInput) throws DukeException {
        if (otherInput.isEmpty()) {
            throw new DukeException("Task number required for the done command.");
        } else if (!otherInput.chars().allMatch(Character::isDigit)) {
            throw new DukeException("Only positive integers allowed for the done command.");
        }

        int taskNo = Integer.parseInt(otherInput);

        if (taskNo <= 0 || taskNo > tasks.size()) {
            throw new DukeException("Task " + taskNo + " does not exist.");
        }

        Task task = tasks.get(taskNo - 1);

        task.markAsDone();
        printPrompt("Nice! I've marked this task as done:\n  "
                + task);
    }

    public static void deleteTask(String otherInput) throws DukeException {
        if (otherInput.isEmpty()) {
            throw new DukeException("Task number required for the delete command.");
        } else if (!otherInput.chars().allMatch(Character::isDigit)) {
            throw new DukeException("Only positive integers allowed for the delete command.");
        }

        int taskNo = Integer.parseInt(otherInput);

        if (taskNo <= 0 || taskNo > tasks.size()) {
            throw new DukeException("Task " + taskNo + " does not exist.");
        }

        Task task = tasks.get(taskNo - 1);

        tasks.remove(task);
        printPrompt("Noted. I've removed this task:\n  "
                + task + "\n" + "Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
    }

    public static void listTasks() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks in your list.");
        }

        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");

        for (Task task : tasks) {
            output.append(tasks.indexOf(task) + 1).append(".").append(task).append('\n');
        }

        printPrompt(output.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            int index = input.indexOf(" ");
            String command = index == -1 ? input : input.substring(0, index);
            String otherInput = index == -1 ? "" : input.substring(index + 1);

            try {
                switch (command) {
                    case "list":
                        if (!otherInput.isEmpty()) {
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }

                        listTasks();

                        break;
                    case "done":
                        doTask(otherInput);

                        break;
                    case "delete":
                        deleteTask(otherInput);

                        break;
                    case "todo":
                        addTodo(otherInput);

                        break;
                    case "deadline":
                        addDeadline(otherInput);

                        break;
                    case "event":
                        addEvent(otherInput);

                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printPrompt(e.getMessage());
            }

            input = scanner.nextLine();
        }

        scanner.close();
        printPrompt("Bye. Hope to see you again soon!");
    }
}
