import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    final static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        speak("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        String userInput;
        while (!(userInput = scanner.nextLine()).equals("bye")) {
            if (userInput.equals("list")) {
                StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

                for (int i = 0; i < Duke.tasks.size(); i++) {
                    sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
                }

                Duke.speak(sb.toString().trim());
            } else if (userInput.startsWith("done")) {
                String[] commandArgs = userInput.split(" ");

                if (commandArgs.length < 2) {
                    throw new IllegalArgumentException("taskId not found!");
                }

                int taskId = Integer.parseInt(commandArgs[1]);
                Task task = tasks.get(taskId - 1);
                task.markAsDone();

                Duke.speak(String.format("Nice! I've marked this task as done:\n%s", task));
            } else {
                Task task;
                String[] commandArgs = userInput.split(" ", 2);

                if (commandArgs.length < 2) {
                    throw new IllegalArgumentException("Attempted to create new task without providing details!");
                }

                if (commandArgs[0].equals("todo")) {
                    task = new Todo(commandArgs[1]);
                } else if (commandArgs[0].equals("deadline")) {
                    String[] taskArgs = commandArgs[1].split("/by", 2);

                    if (taskArgs.length < 2) {
                        throw new IllegalArgumentException("Attempted to create deadline without specifying deadline!");
                    }

                    task = new Deadline(taskArgs[0].trim(), taskArgs[1].trim());

                } else if (commandArgs[0].equals("event")) {
                    String[] taskArgs = commandArgs[1].split("/at", 2);

                    if (taskArgs.length < 2) {
                        throw new IllegalArgumentException("Attempted to create event without specifying time!");
                    }

                    task = new Event(taskArgs[0].trim(), taskArgs[1].trim());
                } else {
                    throw new IllegalArgumentException("Unrecognised command!");
                }

                tasks.add(task);

                Duke.speak(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                        task, tasks.size()));
            }
        }

        Duke.speak("Bye. Hope to see you again soon!");

        scanner.close();

    }

    public static void speak(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", horizontalLine, message, horizontalLine);
    }

}
