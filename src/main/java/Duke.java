import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    private List<Task> tasks = new ArrayList<>();

    private void activate() {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello! I'm Duke\nWhat can I do for you?\n\nMe: ";
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
        System.out.println("Duke: Adios!\n");
    }

    private void showTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            String message = String.valueOf(i + 1) + ". " + task;
            System.out.println(message);
        }
        System.out.print("\nMe: ");
    }

    private void markAsDone(String input) {
        int taskId = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
        String message;

        if (taskId >= 0 && taskId < tasks.size()) {
            Task task = tasks.get(taskId).markAsDone();
            tasks.set(taskId, task);
            message = "Duke: Nice! I've marked it done - " + task.toString();
        } else {
            message = "Duke: 404 task not found. Please enter the correct task ID";
        }

        System.out.println(message);
        System.out.print("\nMe: ");
    }

    private void add(String input) {
        tasks.add(new Task(input));
        System.out.println("Duke: Added '" + input + "' to list of tasks");
        System.out.print("\nMe: ");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.activate();
    }
}
