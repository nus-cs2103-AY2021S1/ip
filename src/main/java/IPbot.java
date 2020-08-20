import exceptions.CommandException;
import exceptions.IPException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IPbot {

    // command strings
    private static final String EXIT_CMD = "bye";
    private static final String LIST_CMD = "list";
    private static final String DONE_CMD = "done";
    private static final String TODO_CMD = "todo";
    private static final String EVENT_CMD = "event";
    private static final String DEADLINE_CMD = "deadline";
    private static final String DELETE_CMD = "delete";


    // user input
    private static final Scanner sc = new Scanner(System.in);

    // task list
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Entry point of the program.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        print("Hello from iPbot, what can I do for you?");

        if (!sc.hasNextLine()) {
            print("No input found. Exiting!");
            return;
        }

        Stream.generate(sc::nextLine)
            .takeWhile(input -> !EXIT_CMD.equals(input))
            .forEach(input -> {
                if (input.isBlank()) {
                    return;
                }

                try {
                    final String output;
                    if (LIST_CMD.equals(input)) {
                        // list tasks
                        output = listTasks();
                    } else if (input.startsWith(DONE_CMD)) {
                        // mark a task as done
                        final String stripped = input.substring(DONE_CMD.length()).strip();
                        try {
                            final int id = Integer.parseInt(stripped);
                            output = completeTask(tasks.get(id - 1));
                        } catch (NumberFormatException e) {
                            throw new CommandException(input, "Could not read the task ID");
                        } catch (IndexOutOfBoundsException e) {
                            throw new CommandException(input, "Task ID does not exist");
                        }
                    } else if (input.startsWith(TODO_CMD)) {
                        // add to do task
                        final String stripped = input.substring(TODO_CMD.length()).strip();
                        output = addTasks(new Todo(stripped));
                    } else if (input.startsWith(EVENT_CMD)) {
                        // add event task
                        final String stripped = input.substring(EVENT_CMD.length()).strip();
                        final String[] split = stripped.split("/at", 2);
                        if (split.length < 2) {
                            throw new CommandException(input,
                                    "Events should have a time specified with /at");
                        }
                        output = addTasks(new Event(split[0].strip(), split[1].strip()));
                    } else if (input.startsWith(DEADLINE_CMD)) {
                        // add deadline task
                        final String stripped = input.substring(DEADLINE_CMD.length()).strip();
                        final String[] split = stripped.split("/by", 2);
                        if (split.length < 2) {
                            throw new CommandException(input,
                                    "Deadlines should have a time due specified with /by");
                        }
                        output = addTasks(new Deadline(split[0].strip(), split[1].strip()));
                     } else if (input.startsWith(DELETE_CMD)) {
                        // delete task
                        final String stripped = input.substring(DELETE_CMD.length()).strip();
                        try {
                            final int id = Integer.parseInt(stripped);
                            output = deleteTask(tasks.get(id - 1));
                        } catch (NumberFormatException e) {
                            throw new CommandException(input, "Could not read the task ID");
                        } catch (IndexOutOfBoundsException e) {
                            throw new CommandException(input, "Task ID does not exist");
                        }
                    } else {
                        throw new CommandException(input, "Unknown command");
                    }
                    print(output);
                } catch (IPException e) {
                    print(e.toString());
                }
            });

        sc.close();
        print("Goodbye!");
    }

    /**
     * Adds a task to the list of tasks.
     * @param toAdd the task to be added
     * @return String containing output generated from iPbot
     */
    private static String addTasks(Task toAdd) {
        tasks.add(toAdd);
        return String.format("added: %s\nThere are now %d tasks.",
                toAdd.toString(), tasks.size());
    }

    /**
     * Queries the list of tasks and displays them.
     * @return String containing output generated from iPbot
     */
    private static String listTasks() {
        return tasks.isEmpty()
                ? "No tasks added."
                : tasks.stream()
                    .map(new Function<Task, String>() {
                        int id = 1;
                        public String apply(Task t) {
                            return String.format("%d. %s", id++, t.toString());
                        }
                    })
                    .collect(Collectors.joining("\n"));
    }

    /**
     * Marks a task as done.
     * @param toComplete the task to be marked as done
     * @return String containing output generated from iPbot
     */
    private static String completeTask(Task toComplete) {
        if (toComplete.getDoneStatus()) {
            // already done
            return "Task already done:\n" + toComplete;
        } else {
            // mark as done
            toComplete.markAsDone();
            return "Task done:\n" + toComplete;
        }
    }

    /**
     * Deletes a task from the task list.
     * @param toDelete the task to be deleted
     * @return String containing output generated from iPbot
     */
    private static String deleteTask(Task toDelete) {
        tasks.remove(toDelete);
        return String.format("Task deleted: %s\n%d task(s) left.",
                toDelete.toString(), tasks.size());
    }

    /**
     * Prints output generated by the bot.
     * @param output string of output to be printed
     */
    public static void print(String output) {
        System.out.println("____________________________________________________________");
        System.out.println(output);
        System.out.println("____________________________________________________________");
        System.out.println();
    }
}
