import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Tickbot {
    private static Scanner inputScanner = new Scanner(System.in);
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printMessage("Hello, this is tickbot! How can I help you?");
        boolean running = true;
        while (running) {
            System.out.print("==> ");
            if (!inputScanner.hasNextLine()) {
                break; // end of file
            }
            String command = inputScanner.nextLine();
            running = processCommand(command);
        }
    }

    private static boolean processCommand(String command) {
        if (command.isBlank()) {
            return true; // empty line, continue inputing
        }
        String[] args = command.split("\\s+");
        switch (args[0]) {
            case "bye": {
                printMessage("See you next time!");
                return false; // end of input
            }
            case "done": {
                try {
                    int index = Integer.parseInt(args[1]) - 1;
                    Task task = tasks.get(index);
                    if (task.isCompleted()) {
                        printMessage("Task already completed.");
                    } else {
                        task.setCompleted(true);
                        printMessage("Task completed: " + task);
                    }
                } catch (NumberFormatException err) {
                    printMessage("Invalid Syntax.");
                    printMessage("Usage: done <task_index>");
                } catch (IndexOutOfBoundsException err) {
                    printMessage("Sorry, No such task found.");
                }
                break;
            }
            case "delete": {
                try {
                    int index = Integer.parseInt(args[1]) - 1;
                    Task task = tasks.get(index);
                    tasks.remove(index);
                    printMessage("Task removed: " + task);
                    printMessage("You have " + tasks.size() + " task(s) in task list.");
                } catch (NumberFormatException err) {
                    printMessage("Invalid Syntax.");
                    printMessage("Usage: delete <task_index>");
                } catch (IndexOutOfBoundsException err) {
                    printMessage("Sorry, No such task found.");
                }
                break;
            }
            case "list": {
                printMessage("Task list:");
                for (int i = 0; i < tasks.size(); i++) {
                    String message = String.format("%d. %s", i + 1, tasks.get(i));
                    printMessage(message);
                }
                break;
            }
            case "todo": {
                processAddTask(args, "TO-DO", (content, _time) -> new Todo(content), null);
                break;
            }
            case "deadline": {
                processAddTask(args, "Deadline", Deadline::new, "/by");
                break;
            }
            case "event": {
                processAddTask(args, "Event", Event::new, "/at");
                break;
            }
            default: {
                printMessage("Unknown command " + args[0] + ".");
            }
        }
        return true; // continue inputing
    }

    private static void printUsage(String[] args, String timeMarker) {
        if (timeMarker != null) {
            printMessage(String.format("Usage: %s <content> %s <time>",
                args[0], timeMarker));
        } else {
            printMessage(String.format("Usage: %s <content>", args[0]));
        }
    }

    private static void processAddTask(
        String[] args,
        String taskName,
        BiFunction<String, LocalDate, ? extends Task> initializer,
        String timeMarker
    ) {
        if (args.length < 2) {
            printMessage("Please input the content of the " + args[0] + ".");
            printUsage(args, timeMarker);
            return;
        }
        String content = args[1];
        Optional<String> time = Optional.empty();
        try {
            for (int i = 2; i < args.length; i++) {
                if (time.isPresent()) {
                    time = Optional.of(time.get() + " " + args[i]);
                } else if (Objects.equals(args[i], timeMarker)) {
                    time = Optional.of(args[++i]);
                } else {
                    content += " " + args[i];
                }
            }
            Task task = initializer.apply(content,
                timeMarker == null ? null : LocalDate.parse(time.get()));
            tasks.add(task);
            printMessage(taskName + " added: " + task);
            printMessage("You have " + tasks.size() + " task(s) in task list.");
        } catch (IndexOutOfBoundsException err) {
            printMessage("Please input valid time after " + timeMarker + ".");
            printUsage(args, timeMarker);
        } catch (NoSuchElementException err) {
            printMessage("Missing time for the " + args[0] + ".");
            printUsage(args, timeMarker);
        } catch (DateTimeException err) {
            printMessage("Bad date format. Please input in YYYY-MM-DD format.");
        }
    }

    private static void printMessage(String message) {
        System.out.println("  " + message);
    }
}
