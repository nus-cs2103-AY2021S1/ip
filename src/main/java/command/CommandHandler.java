package command;

import core.Storage;
import core.TaskList;
import core.Ui;
import exceptions.CommandException;
import exceptions.IPException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandHandler implements Consumer<String> {

    private final boolean printOutput;

    public CommandHandler(boolean printOutput) {
        this.printOutput = printOutput;
    }

    @Override
    public void accept(String input) {
        if (input.isBlank()) {
                return;
            }

            try {
                final String output;
                final Command cmd = Parser.parseCommand(input);
                final String stripped = cmd.strip(input);
                final String[] split;
                switch (cmd) {
                    case LIST_CMD:
                        output = listTasks();
                        break;
                    case DONE_CMD:
                        try {
                            final int id = Integer.parseInt(stripped);
                            output = completeTask(TaskList.get(id - 1));
                        } catch (NumberFormatException e) {
                            throw new CommandException(input, "Could not read the task ID");
                        } catch (IndexOutOfBoundsException e) {
                            throw new CommandException(input, "Task ID does not exist");
                        }
                        break;
                    case TODO_CMD:
                        output = addTask(new Todo(stripped));
                        break;
                    case EVENT_CMD:
                        split = stripped.split("/at", 2);
                        if (split.length < 2) {
                            throw new CommandException(input,
                                    "Events should have a time specified with /at");
                        }
                        output = addTask(new Event(split[0].strip(), split[1].strip()));
                        break;
                    case DEADLINE_CMD:
                        split = stripped.split("/by", 2);
                        if (split.length < 2) {
                            throw new CommandException(input,
                                    "Deadlines should have a time due specified with /by");
                        }
                        output = addTask(new Deadline(split[0].strip(), split[1].strip()));
                        break;
                    case DELETE_CMD:
                        try {
                            final int id = Integer.parseInt(stripped);
                            output = deleteTask(id - 1);
                        } catch (NumberFormatException e) {
                            throw new CommandException(input, "Could not read the task ID");
                        } catch (IndexOutOfBoundsException e) {
                            throw new CommandException(input, "Task ID does not exist");
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected state");
                }
                if (printOutput) {
                    Ui.print(output);
                }
                Storage.save();
            } catch (IPException e) {
                Ui.print(e.toString());
            } catch (IOException e) {
                Ui.print(e.getMessage());
            }
    }

    /**
     * Adds a task to the list of tasks.
     * @param toAdd the task to be added
     * @return String containing output generated from iPbot
     */
    private static String addTask(Task toAdd) {
        TaskList.add(toAdd);
        return String.format("added: %s\nThere are now %d task(s).",
                toAdd.toString(), TaskList.size());
    }

    /**
     * Queries the list of tasks and displays them.
     * @return String containing output generated from iPbot
     */
    private static String listTasks() {
        return TaskList.size() == 0
                ? "No tasks added."
                : TaskList.stream()
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
     * @param id the ID of the task to be deleted
     * @return String containing output generated from iPbot
     */
    private static String deleteTask(int id) {
        return String.format("Task deleted: %s\n%d task(s) left.",
                TaskList.remove(id).toString(), TaskList.size());
    }

}
