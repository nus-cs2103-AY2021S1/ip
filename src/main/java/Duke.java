import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Duke {

    private List<Task> tasks = new ArrayList<>();

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
        if (input.equals("bye")) {
            quit();
        } else if (input.equals("list")) {
            showTasks();
        } else if (input.contains("done")){
            markAsDone(input);
        } else {
            add(input);
        }
    }

    private void quit() {
        System.out.println("Adios!");
    }

    private void showTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            String message = String.valueOf(i + 1) + ". " + task;
            System.out.println(message);
        }
        System.out.print("\n");
    }

    private void markAsDone(String input) {
        int taskId = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
        String message;

        if (taskId >= 0 && taskId < tasks.size()) {
            Task task = tasks.get(taskId).markAsDone();
            tasks.set(taskId, task);
            message = "Nice! I've marked it done - " + task.toString();
        } else {
            message = "404 task not found. Please enter the correct task ID";
        }

        System.out.println(message);
        System.out.print("\n");
    }

    private void add(String input) {
        String[] splited = input.split("\\s+");
        Task task;
        if (splited[0].equals("todo")) {
            task = createTodo(splited);
        } else if (splited[0].equals("deadline")) {
            task = createDeadline(splited);
        } else if (splited[0].equals("event")) {
            task = createEvent(splited);
        } else {
            task = new Task(input);
        }

        tasks.add(task);
        System.out.println("Added '" + task.toString() + "' to list of tasks");
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.print("\n");
    }

    private Task createTodo(String[] input) {
        String[] title = Arrays.copyOfRange(input, 1, input.length);
        return new Todo(String.join(" ", title));
    }

    private Task createDeadline(String[] input) {
        int separator = getIndex(input, "/by");
        String[] titles = Arrays.copyOfRange(input, 1, separator);
        String[] deadlines = Arrays.copyOfRange(input, separator + 1, input.length);
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

    private Task createEvent(String[] input) {
        int separator = getIndex(input, "/at");
        String[] titles = Arrays.copyOfRange(input, 1, separator);
        String[] times = Arrays.copyOfRange(input, separator + 1, input.length);
        String title = String.join(" ", titles);
        String time = String.join(" ", times);
        return new Event(title, time);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.activate();
    }
}
