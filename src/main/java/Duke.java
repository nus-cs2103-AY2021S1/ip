import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws Exception {
        System.out.println("Hi there, I'm TARS!\nWhat can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String operation = "start";

        while (!operation.equals("bye")) {
            String[] line = scanner.nextLine().split(" ", 2);
            operation = line[0];
            switch (operation) {
                case "list":
                    if (tasks.size() == 0) {
                        System.out.println("The list is empty! Add in some new tasks!");
                    }
                    int counter = 1;
                    for (Task item : tasks) {
                        System.out.println(counter + ". " + item);
                        counter++;
                    }
                    break;

                case "done":
                    int task_index = Integer.parseInt(line[1]);
                    // Validate task index
                    if (task_index <= tasks.size()) {
                        Task task = tasks.get(task_index - 1);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[" + task.getStatusIcon() + "]" + " " + task.description);
                    } else {
                        throw new DukeException("Only existing tasks with index <= " + tasks.size() + " can be marked as done!");
                    }
                    break;

                case "todo":
                case "deadline":
                case "event":
                    Task task;
                    // Validate descriptions
                    if (line.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of " + operation +" cannot be empty.");
                    } else if (operation.equals("todo")) {
                        String description = line[1];
                        task = new ToDo(description);
                        tasks.add(task);
                    } else if (operation.equals("deadline")) {
                        String[] description = line[1].split(" /by ");
                        // Validate date/time
                        if (description.length < 2) {
                            throw new DukeException("When is it due? Give me a date/time!");
                        }
                        task = new Deadline(description[0], description[1]);
                        tasks.add(task);
                    } else {
                        String[] description = line[1].split(" /at ");
                        // Validate date/time
                        if (description.length < 2) {
                            throw new DukeException("When is it? Give me a date/time!");
                        }
                        task = new Event(description[0], description[1]);
                        tasks.add(task);
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    break;
                default:
                    // Handles all other inputs
                    if (!operation.equals("bye")) {
                        throw new DukeException("☹ Sorry, I don't recognise that command!");
                    }
            }
        }
        System.out.println("Bye bye. See you again soon!");
    }
}