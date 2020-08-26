import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class Duke {

    UserData userData = new UserData();

    private void activate() {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello! I'm Duke\nWhat can I do for you?\n\n";
        System.out.print(greeting);

        String input = "";

        while (!input.equals("bye") && sc.hasNextLine()) {
            input = sc.nextLine();
            reply(input);
        }
    }

    private void reply(String input) {
        String[] splitted = input.split("\\s+");
        String command = splitted[0];
        if (input.equals("help")) {
            help();
        } else if (command.equals("bye")) {
            quit();
        } else if (command.equals("list")) {
            showTasks();
        } else if (command.equals("done")){
            markAsDone(input);
        } else if (command.equals("delete")) {
            delete(input);
        } else {
            add(input);
        }
    }

    private void help() {
        String msg = String.join("\n",
                                "Adding task:",
                                         "todo <desc>",
                                         "deadline <desc> /by <desc>",
                                         "event <desc> /at <desc>",
                                         "",
                                         "list - show all added tasks",
                                         "done <taskId> - mark the task as done",
                                         "delete <taskId> - delete the task",
                                         "bye - close Duke");
        System.out.println(msg + "\n");
    }

    private void quit() {
        System.out.println("Adios!");
    }

    private void showTasks() {
        List<Task> tasks = userData.getTasks();
        if (tasks.size() == 0) {
            System.out.println("No tasks in the list wohoo!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                String task = tasks.get(i).toString();
                String message = (i + 1) + ". " + task;
                System.out.println(message);
            }
        }
        System.out.print("\n");
    }

    private void markAsDone(String input) {
        String rawNum = input.replaceAll("[^0-9]", "");
        int taskId = Integer.parseInt(rawNum) - 1;

        try {
            if (taskId < 0 || taskId >= userData.taskSize())
                throw new InvalidTaskIdException(rawNum);

            Task task = userData.markAsDone(taskId);
            System.out.println("Nice! I've marked it done - " + task.toString());
            System.out.print("\n");

        } catch (DukeException e) {
            System.out.println(e + "\n");
        }
    }

    private void delete(String input) {
        String rawNum = input.replaceAll("[^0-9]", "");
        int taskId = Integer.parseInt(rawNum) - 1;

        try {
            if (taskId < 0 || taskId >= userData.taskSize())
                throw new InvalidTaskIdException(rawNum);

            Task task = userData.delete(taskId);
            System.out.println("Noted! I've removed this task - " + task.toString());
            summary();
            System.out.print("\n");

        } catch (DukeException e) {
            System.out.println(e + "\n");
        }
    }

    private void add(String input) {
        String[] splitted = input.split("\\s+");
        String command = splitted[0];
        Task task;

        try {
            if (command.equals("todo")) {
                task = createTodo(splitted);
            } else if (command.equals("deadline")) {
                task = createDeadline(splitted);
            } else if (command.equals("event")) {
                task = createEvent(splitted);
            } else {
                throw new InvalidCommandException();
            }

            userData.create(task);
            System.out.println("Added '" + task.toString() + "' to list of tasks");
            summary();
            System.out.print("\n");
        } catch (DukeException e) {
            System.out.println(e + "\n");
        }

    }

    private Task createTodo(String[] input) throws EmptyDescriptionException {
        String[] title = Arrays.copyOfRange(input, 1, input.length);
        if (title.length == 0) throw new EmptyDescriptionException("ToDo");
        return new Todo(String.join(" ", title));
    }

    private Task createDeadline(String[] input) throws EmptyDescriptionException, InvalidFormatException {
        if (input.length == 1) throw new EmptyDescriptionException("Deadline");

        int separator = getIndex(input, "/by");

        if (separator == -1) throw new InvalidFormatException("/by parameter");

        String[] titles = Arrays.copyOfRange(input, 1, separator);
        String[] deadlines = Arrays.copyOfRange(input, separator + 1, input.length);

        if (deadlines.length == 0) throw new EmptyDescriptionException("/by parameter");

        String title = String.join(" ", titles);
        String deadline = String.join(" ", deadlines);
        return new Deadline(title, deadline);
    }

    private int getIndex(String[] words, String target) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) return i;
        }
        return -1;
    }

    private Task createEvent(String[] input) throws EmptyDescriptionException, InvalidFormatException {
        if (input.length == 1) throw new EmptyDescriptionException("Event");

        int separator = getIndex(input, "/at");

        if (separator == -1) throw new InvalidFormatException("/at parameter");

        String[] titles = Arrays.copyOfRange(input, 1, separator);
        String[] times = Arrays.copyOfRange(input, separator + 1, input.length);

        if (times.length == 0) throw new EmptyDescriptionException("/at parameter");

        String title = String.join(" ", titles);
        String time = String.join(" ", times);
        return new Event(title, time);
    }

    private void summary() {
        System.out.println("Now you have " + userData.taskSize() + " tasks in the list");
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.activate();
    }
}
