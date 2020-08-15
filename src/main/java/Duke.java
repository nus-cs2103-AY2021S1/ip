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
            try {
                parseInputs(userInput);
            } catch (DukeException e) {
                speak(e.getMessage());
            }
        }

        Duke.speak("Bye. Hope to see you again soon!");

        scanner.close();
    }

    public static void speak(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", horizontalLine, message, horizontalLine);
    }

    public static void parseInputs(String userInput) throws DukeException {
        String[] commandInputs = userInput.split(" ", 2);

        if (commandInputs.length == 0) {
            throw new DukeException("Something went wrong when parsing your inputs!");
        }

        Command command;
        try {
            command = Command.valueOf(commandInputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            command = Command.UNKNOWN;
        }

        String commandDetails;

        switch (command) {
            case LIST:
                StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

                for (int i = 0; i < Duke.tasks.size(); i++) {
                    sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
                }

                Duke.speak(sb.toString().trim());
                break;

            case DONE:
                if (commandInputs.length < 2) {
                    throw new DukeException("Attempted to mark a task as done, but no task was specified!");
                }

                commandDetails = commandInputs[1];

                try {
                    int taskId = Integer.parseInt(commandDetails);
                    Task task = tasks.get(taskId - 1);
                    task.markAsDone();

                    Duke.speak(String.format("Nice! I've marked this task as done:\n%s", task));
                } catch (NumberFormatException e) {
                    throw new DukeException(
                            "Please key in only the integer representing the task you want to mark as complete!");
                }

                break;

            case DELETE:
                if (commandInputs.length < 2) {
                    throw new DukeException("Attempted to delete a task, but no task was specified!");
                }

                commandDetails = commandInputs[1];

                try {
                    int taskId = Integer.parseInt(commandDetails);
                    Task task = tasks.remove(taskId - 1);

                    Duke.speak(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                            task, tasks.size()));
                } catch (NumberFormatException e) {
                    throw new DukeException("Please key in only the integer representing the task you want to delete!");
                }

                break;

            case TODO:
            case DEADLINE:
            case EVENT:
                Task task;

                if (commandInputs.length < 2) {
                    throw new DukeException("Attempted to create new task without providing details!");
                }

                commandDetails = commandInputs[1];

                if (command == Command.TODO) {
                    task = new Todo(commandDetails);
                } else if (command == Command.DEADLINE) {
                    String[] deadlineDetails = commandDetails.split("/by", 2);

                    if (deadlineDetails.length < 2) {
                        throw new DukeException("Attempted to create task with deadline without specifying deadline!");
                    }

                    String description = deadlineDetails[0].trim();
                    String by = deadlineDetails[1].trim();

                    task = new Deadline(description, by);
                } else {
                    // Last case would be creating an event
                    String[] eventDetails = commandDetails.split("/at", 2);

                    if (eventDetails.length < 2) {
                        throw new DukeException("Attempted to create event without specifying time!");
                    }

                    String description = eventDetails[0].trim();
                    String at = eventDetails[1].trim();

                    task = new Event(description, at);
                }

                tasks.add(task);

                Duke.speak(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                        task, tasks.size()));
                break;

            case UNKNOWN:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
