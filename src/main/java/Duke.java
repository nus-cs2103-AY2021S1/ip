import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public static Todo addTodo(String description) throws DukeException {
        return addTodo(description, false);
    }

    public static Todo addTodo(String description, boolean isDone) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(description, isDone);

        tasks.add(todo);
        return todo;
    }

    public static Deadline addDeadline(String input) throws DukeException {
        int index = input.indexOf(" /by ");
        String description = index == -1 ? input : input.substring(0, index);
        String by = index == -1 ? "" : input.substring(index + 5);

        return addDeadline(description, by, false);
    }

    public static Deadline addDeadline(String description, String by, boolean isDone) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (by.equals("")) {
            throw new DukeException("The date/time of an event cannot be empty.");
        }

        Deadline deadline = new Deadline(description, by, isDone);

        tasks.add(deadline);
        return deadline;
    }

    public static Event addEvent(String input) throws DukeException {
        int index = input.indexOf(" /at ");
        String description = index == -1 ? input : input.substring(0, index);
        String at = index == -1 ? "" : input.substring(index + 5);

        return addEvent(description, at, false);
    }

    public static Event addEvent(String description, String at, boolean isDone) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (at.equals("")) {
            throw new DukeException("The date/time of an event cannot be empty.");
        }

        Event event = new Event(description, at, isDone);

        tasks.add(event);
        return event;
    }

    public static Task doTask(String otherInput) throws DukeException {
        if (otherInput.equals("")) {
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
        return task;
    }

    public static Task deleteTask(String otherInput) throws DukeException {
        if (otherInput.equals("")) {
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
        return task;
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

    public static void saveTasks() throws DukeException {
        try {
            Path path = Paths.get("data");
            Files.createDirectories(path);
            Path file = Paths.get("data/duke.txt");

            List<String> data = tasks.stream().map(Task::toSaveData).collect(Collectors.toList());
            Files.write(file, data);
        } catch(IOException e) {
            throw new DukeException("Unable to save file! Exiting without saving :-(");
        }
    }

    public static void loadTasks() throws DukeException {
        Path file = Paths.get("data/duke.txt");

        if (Files.exists(file)) {
            try {
                List<String> data = Files.readAllLines(file);

                for (String line : data) {
                    String[] params = line.split("\\s\\|\\s");
                    String type = params[0];
                    String description = params[2];
                    boolean isDone = params[1].equals("1");

                    if (!params[1].equals("0") && !params[1].equals("1")) {
                        throw new DukeException();
                    }

                    switch (type) {
                        case "T":
                            addTodo(description, isDone);

                            break;
                        case "D":
                            addDeadline(description, params[3], isDone);

                            break;
                        case "E":
                            addEvent(description, params[3], isDone);

                            break;
                        default:
                            throw new DukeException();
                    }
                }
            } catch(DukeException | IOException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Data file is corrupt. Ignoring saved tasks :-(");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printGreeting();
        try {
            loadTasks();
        } catch(DukeException e) {
            printPrompt(e.getMessage());
        }

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            int index = input.indexOf(" ");
            String command = index == -1 ? input : input.substring(0, index);
            String otherInput = index == -1 ? "" : input.substring(index + 1);

            try {
                switch (command) {
                    case "list":
                        if (!otherInput.equals("")) {
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }

                        listTasks();

                        break;
                    case "done":
                        Task doneTask = doTask(otherInput);
                        printPrompt("Nice! I've marked this task as done:\n  "
                                + doneTask);

                        break;
                    case "delete":
                        Task deletedTask = deleteTask(otherInput);
                        printPrompt("Noted. I've removed this task:\n  "
                                + deletedTask + "\n" + "Now you have " + tasks.size()
                                + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");

                        break;
                    case "todo":
                        Todo todo = addTodo(otherInput);
                        printAddTask(todo);

                        break;
                    case "deadline":
                        Deadline deadline = addDeadline(otherInput);
                        printAddTask(deadline);

                        break;
                    case "event":
                        Event event = addEvent(otherInput);
                        printAddTask(event);

                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException e) {
                printPrompt(e.getMessage());
            }

            input = scanner.nextLine();
        }

        scanner.close();
        try {
            saveTasks();
        } catch(DukeException e) {
            printPrompt(e.getMessage());
        }
        printPrompt("Bye. Hope to see you again soon!");
    }
}
