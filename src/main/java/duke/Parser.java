package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    private static boolean isExit = false;
    private static TaskList tasks;
    private static Storage storage;
    private static Task currentTask;

    /**
     * Sets Parser's tasks to that of Duke's
     *
     * @param tasks from Duke
     */
    public static void setTasks(TaskList tasks) {
        Parser.tasks = tasks;
    }

    /**
     * Sets Parser's storage to that of Duke's
     *
     * @param storage from Duke
     */
    public static void setStorage(Storage storage) {
        Parser.storage = storage;
    }

    public static boolean getExitStatus() {
        return Parser.isExit;
    }

    private static String outputDoneMessage(int taskToMark) throws IOException {
        String output = "Task Accomplished! I've marked this task as done:\n";
        tasks.get(taskToMark).markAsDone();
        output += tasks.get(taskToMark);
        storage.refresh(tasks);
        return output;
    }

    private static String outputDeleteMessage(int taskToDelete) throws IOException {
        String output = "Alright! I've removed this task:\n";
        output += tasks.remove(taskToDelete) + "\n";
        output += "Now you have " + tasks.size() + " tasks in your list.";
        storage.refresh(tasks);
        return output;
    }

    private static String outputTaskMessage(Task currentTask) throws IOException {
        String output = "Got it! I've added this task:\n";
        tasks.add(currentTask);
        storage.append(currentTask);
        output += tasks.get(tasks.size() - 1) + "\n";
        output += "Now you have " + tasks.size() + " tasks in your list.";
        return output;
    }

    private static String outputDuplicateMessage(Task task) {
        String duplicateMessage = "The existing tasks have the same description:\n";
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task currentTask = tasks.get(i - 1);
            if (currentTask.getDescription().equals(task.getDescription())) {
                duplicateMessage += i + ". " + currentTask + "\n";
            }
        }
        duplicateMessage += "Enter \"yes\" if you wish to keep task or \"no\" otherwise.";
        Parser.currentTask = task;
        return duplicateMessage;
    }

    private static boolean hasDuplicates(String taskString) {
        for (Task task : tasks.getListOfTasks()) {
            if (task.getDescription().equals(taskString)) {
                return true;
            }
        }
        return false;
    }

    private static String executeExit() {
        Parser.isExit = true;
        return "Goodbye, have a nice day :D";
    }

    private static String executeList() {
        String output = "Here are the tasks in your list:" + "\n";
        for (int i = 1; i < tasks.size() + 1; i++) {
            output += i + ". " + tasks.get(i - 1) + "\n";
        }
        return output;
    }

    private static String executeDone(String command) throws IOException {
        if (command.trim().length() == 4) {
            return new DukeException("Hold up! Please specify which task is done.").getMessage();
        }
        int taskToMark = Integer.parseInt(command.substring(5)) - 1;
        if (taskToMark >= tasks.size()) {
            return new DukeException("Hold up! Please specify a valid task number.").getMessage();
        }

        return outputDoneMessage(taskToMark);
    }

    private static String executeDelete(String command) throws IOException {
        if (command.trim().length() == 6) {
            return new DukeException("Hold up! Please specify which task to delete.").getMessage();
        }
        int taskToDelete = Integer.parseInt(command.substring(7)) - 1;
        if (taskToDelete >= tasks.size()) {
            return new DukeException("Hold up! Please specify a valid task number.").getMessage();
        }

        return outputDeleteMessage(taskToDelete);
    }

    private static String executeFind(String command) {
        if (command.trim().length() == 4) {
            return new DukeException("Hold up! Please specify keyword.").getMessage();
        }

        String output = "Here are the matching tasks in your list:\n";
        final int outputLength = output.length();
        String keyword = command.substring(5);
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task currentTask = tasks.get(i - 1);
            if (currentTask.toString().contains(keyword)) {
                output += i + ". " + currentTask + "\n";
            }
        }
        return (output.length() == outputLength) ? "There are no matching tasks!" : output;
    }

    private static String executeTodo(String command) throws IOException {
        if (command.trim().length() == 4) {
            return new DukeException("Hold up! The description of todo cannot be empty...").getMessage();
        }

        String taskString = command.substring(5);
        ToDo currentTask = new ToDo(taskString);
        if (hasDuplicates(taskString)) {
            return outputDuplicateMessage(currentTask);
        }
        return outputTaskMessage(currentTask);
    }

    private static String executeDeadline(String command) throws IOException {
        if (command.trim().length() == 8) {
            return new DukeException("Hold up! The description of deadline cannot be empty...").getMessage();
        } else if (!command.contains("/by")) {
            return new DukeException("Please specify deadline using: /by (deadline)").getMessage();
        }

        int endIndex = command.indexOf("/by") - 1;
        String taskString = command.substring(9, endIndex);
        String dateTimeString = command.substring(endIndex + 5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
            Deadline currentTask = new Deadline(taskString,
                    dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));
            if (hasDuplicates(taskString)) {
                return outputDuplicateMessage(currentTask);
            }
            return outputTaskMessage(currentTask);
        } catch (DateTimeException e) {
            return new DukeException("Please specify date & time in the form yyyy-MM-dd HHmm").getMessage();
        }
    }

    private static String executeEvent(String command) throws IOException {
        if (command.trim().length() == 5) {
            return new DukeException("Hold up! The description of event cannot be empty...").getMessage();
        } else if (!command.contains("/at")) {
            return new DukeException("Please specify timing using: /at (timing)").getMessage();
        }

        int endIndex = command.indexOf("/at") - 1;
        String taskString = command.substring(6, endIndex);
        String atString = command.substring(endIndex + 5);
        Event currentTask = new Event(taskString, atString);
        if (hasDuplicates(taskString)) {
            return outputDuplicateMessage(currentTask);
        }
        return outputTaskMessage(currentTask);
    }

    private static String executeTask(String command) throws IOException {
        if (command.startsWith("todo")) {
            return executeTodo(command);
        } else if (command.startsWith("deadline")) {
            return executeDeadline(command);
        } else if (command.startsWith("event")) {
            return executeEvent(command);
        } else {
            return new DukeException("Sorry, I'm not sure what you mean by that :(").getMessage();
        }
    }

    private static String executeDuplicateHandling(String command) throws IOException {
        assert command.equals("yes") || command.equals("no");
        String output = "";
        if (Parser.currentTask == null) { // scenario where the user entered "yes" or "no" randomly
            return "Invalid command";
        } else if (command.equals("yes")) {
            output += outputTaskMessage(Parser.currentTask);
        }
        Parser.currentTask = null; // clears Parser.currentTask each time duplicate handling is completed
        return output.length() == 0 ? "Noted! The task will not be added." : output;
    }

    /**
     * Takes in a single line of command. isExit becomes true when the String "bye" is entered,
     * prompting the main program to exit.
     * Program stores user inputs as Tasks and returns the list when the String "list" is entered.
     * Tasks are categorised into "todo", "deadline" (to specify "by") and "event"  (to specify "at").
     * When "done xx" is entered, Task xx in the list is marked as done.
     * When "delete xx" is entered, Task xx in the list is removed from the list.
     * When "find xx" is entered, tasks in the task list containing keyword xx will be shown.
     *
     * @param command user input
     * @return duke's reply
     * @throws IOException if filePath does not exist
     */
    public static String parse(String command) throws IOException {
        assert tasks.size() >= 0;

        if (command.equals("bye")) {
            return executeExit();
        } else if (command.equals("list")) {
            return executeList();
        } else if (command.equals("yes") || command.equals("no")) {
            return executeDuplicateHandling(command);
        } else if (command.startsWith("done")) {
            return executeDone(command);
        } else if (command.startsWith("delete")) {
            return executeDelete(command);
        } else if (command.startsWith("find")) {
            return executeFind(command);
        } else {
            return executeTask(command);
        }
    }
}
