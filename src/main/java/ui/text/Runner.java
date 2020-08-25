package ui.text;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import task.*;

public class Runner {
    private TaskList tasks = new TaskList();

    void help(String[] args) {
        if (args.length == 1) {
            Output.printAllUsage();
        } else {
            for (int i = 1; i < args.length; i++) {
                Output.printMessage("Usage of " + args[i] + ":");
                Output.printUsage(args[i]);
            }
        }
    }

    void delete(String[] args) {
        try {
            int index = Integer.parseInt(args[1]) - 1;
            Task task = tasks.get(index);
            tasks.remove(index);
            Output.printMessage("Task removed: " + task);
            Output.printMessage("You have " + tasks.size() + " task(s) in task list.");
        } catch (NumberFormatException err) {
            Output.printMessage("Invalid Syntax.");
            Output.printMessage("Usage: delete <task_index>");
        } catch (IndexOutOfBoundsException err) {
            Output.printMessage("Sorry, No such task found.");
        }
    }

    void done(String[] args) {
        try {
            int index = Integer.parseInt(args[1]) - 1;
            tasks.complete(index);
        } catch (NumberFormatException err) {
            Output.printMessage("Invalid Syntax.");
            Output.printMessage("Usage: done <task_index>");
        } catch (IndexOutOfBoundsException err) {
            Output.printMessage("Sorry, No such task found.");
        }
    }

    void list(String[] args) {
        if (tasks.size() == 0) {
            Output.printMessage("Your task list is empty now.");
            return;
        }
        Output.printMessage("Task list:");
        for (int i = 0; i < tasks.size(); i++) {
            String message = String.format("%d. %s", i + 1, tasks.get(i));
            Output.printMessage(message);
        }
    }

    void todo(String[] args) {
        addTask(args, "TO-DO", (content, _time) -> new Todo(false, content), null);
    }

    void deadline(String[] args) {
        addTask(args, "Deadline", (content, time) -> new Deadline(false, content, time), "/by");
    }

    void event(String[] args) {
        addTask(args, "Event", (content, time) -> new Event(false, content, time), "/at");
    }

    private void addTask(
        String[] args,
        String taskName,
        BiFunction<String, LocalDate, ? extends Task> initializer,
        String timeMarker
    ) {
        if (args.length < 2) {
            Output.printMessage("Please input the content of the " + args[0] + ".");
            Output.printUsage(args[0]);
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
            Output.printMessage(taskName + " added: " + task);
            Output.printMessage("You have " + tasks.size() + " task(s) in task list.");
        } catch (IndexOutOfBoundsException err) {
            Output.printMessage("Please input valid time after " + timeMarker + ".");
            Output.printUsage(args[0]);
        } catch (NoSuchElementException err) {
            Output.printMessage("Missing time for the " + args[0] + ".");
            Output.printUsage(args[0]);
        } catch (DateTimeException err) {
            Output.printMessage("Bad date format. Please input in YYYY-MM-DD format.");
        }
    }

}