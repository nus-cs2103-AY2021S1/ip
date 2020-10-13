package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {

    private static Ui ui;
    private static TaskList taskList;
    private static String filePath;

    /**
     * Constructor which takes in a UI and initialises its ui field member.
     *
     * @param ui a UI for the Duke object
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Set the Parser's tasks.
     *
     * @param taskList TaskList object that contains a list of tasks
     */
    public static void setTasks(TaskList taskList) {
        Parser.taskList = taskList;
    }

    /**
     * Set the Parser's filepath for storage.
     *
     * @param filePath the path location of the load or save file
     */
    public static void setFilePath(String filePath) {
        Parser.filePath = filePath;
    }

    private static String executeByeCommand() {
        return ("Bye. Hope to see you again soon! \uD83D\uDE0A");
    }

    private static String executeListCommand() {
        String output = "";
        if (taskList.getTasks().size() == 0) {
            output += "You have no tasks in your list!";
        } else {
            output += "Here are the tasks in your list:\n";
            for (Task task : taskList.getTasks()) {
                int index = taskList.getTasks().indexOf(task) + 1;
                output += (index + "." + task + "\n");
            }
            output += "\n";
        }
        assert taskList.getTasks().size() >= 0;
        return output;
    }

    private static String executeDoneCommand(String[] arrOfStr) throws IOException {
        int index = Integer.parseInt(arrOfStr[1]) - 1;
        Task oldTask = taskList.getTasks().get(index);
        Task newTask = oldTask.markAsDone();
        taskList.replace(oldTask, newTask);
        Storage.updateFile(filePath, taskList);
        String output = "Nice! I've marked this task as done:\n";
        output += (newTask.getStatusIcon() + " " + newTask.description);
        return output;
    }

    private static String executeDeleteCommand(String[] arrOfStr) throws IOException {
        int index = Integer.parseInt(arrOfStr[1]) - 1;
        Task task = taskList.getTasks().get(index);
        taskList.delete(index);
        List<Task> tasks = taskList.getTasks();
        String output = ("Noted. I've removed this task:\n");
        output += (task + "\n");
        output += ("Now you have " + (tasks.size() != 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        assert taskList.getTasks().size() >= 0;
        return output;
    }

    private static String executeFindCommand(String[] arrOfStr) {
        boolean canFind = false;
        String textToMatch = arrOfStr[1];
        String output = ("Here are the matching tasks in your list:\n");
        int index = 1;
        if (taskList != null) {
            for (Task task : taskList.getTasks()) {
                String description = task.getDescription();
                if (description.contains(textToMatch)) {
                    output += (index + "." + task + "\n");
                    index++;
                    canFind = true;
                }
            }
        }
        output += "\n";
        if (!canFind) {
            output = ("There are no matching tasks in your list!");
        }
        return output;
    }

    private static String executeHelpCommand() {
        String output = " Command │       Description        │                   Usage\n"
                + "─────────┼──────────────────────────┼───────────────────────────────────────────\n"
                + "bye      │ exits the program        │ bye\n"
                + "list     │ list all current tasks   │ list\n"
                + "done     │ marks a task done        │ done [task number]\n"
                + "delete   │ deletes a task           │ delete [task number]\n"
                + "find     │ finds all matching tasks │ find [text to match]\n"
                + "help     │ displays help menu       │ help\n"
                + "todo     │ adds a todo task         │ todo [task name]\n"
                + "deadline │ adds a deadline task     │ deadline [task name] /by [dd/MM/yyyy HHmm]\n"
                + "event    │ adds an event task       │ event [task name] /at [duration]";
        return output;
    }

    private static String executeEmptyDescriptionCommand(String identifier) {
        return ("⚠ OOPS!!! The description of a " + identifier + " cannot be empty.");
    }

    private static String executeTodoCommand(String input) throws IOException {
        Task task;
        String[] newArrOfStr = input.split(" ", 2);
        task = new Todo(newArrOfStr[1]);

        taskList.add(task);
        List<Task> tasks = taskList.getTasks();
        String output = ("Got it. I've added this task:\n");
        output += (task + "\n");
        output += ("Now you have " + (tasks.size() != 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        assert taskList.getTasks().size() >= 0;
        return output;
    }

    private static String executeDeadlineCommand(String input) throws IOException {
        Task task;
        String[] firstSplit = input.split(" /by ", 2);
        String[] secondSplit = firstSplit[0].split(" ", 2);
        String description = secondSplit[1];
        String date = firstSplit[1];
        String[] dateSplit = date.split(" ", 0);

        if (dateSplit.length > 1) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
            task = new Deadline(description, localDateTime);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
            task = new Deadline(description, localDate);
        }

        taskList.add(task);
        List<Task> tasks = taskList.getTasks();
        String output = ("Got it. I've added this task:\n");
        output += (task + "\n");
        output += ("Now you have " + (tasks.size() != 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        assert taskList.getTasks().size() >= 0;
        return output;
    }

    private static String executeEventCommand(String input) throws IOException {
        Task task;
        String[] firstSplit = input.split(" /at ", 2);
        String by = firstSplit[1];
        String[] secondSplit = firstSplit[0].split(" ", 2);
        String description = secondSplit[1];
        task = new Event(description, by);

        taskList.add(task);
        List<Task> tasks = taskList.getTasks();
        String output = ("Got it. I've added this task:\n");
        output += (task + "\n");
        output += ("Now you have " + (tasks.size() != 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        assert taskList.getTasks().size() >= 0;
        return output;
    }

    private static String executeWrongInputCommand() {
        String output = ("⚠ OOPS!!! I'm sorry, but I don't know what that means \uD83D\uDE41\n");
        output += ("Type \"help\" for a list of commands");
        return output;
    }

    /**
     * Alternative interact method for GUI.
     * Decides on the relevant actions to execute based on the input of the user.
     *
     * @param input    user input to be parsed
     * @throws IOException produced by failed or interrupted I/O operations
     */
    public static String parse(String input) throws IOException {

        String[] arrOfStr = input.split(" ", 0);
        String identifier = arrOfStr[0];

        switch (identifier) {
            case "bye":
                return executeByeCommand();
            case "list": {
                return executeListCommand();
            }
            case "done": {
                return executeDoneCommand(arrOfStr);
            }
            case "delete": {
                return executeDeleteCommand(arrOfStr);
            }
            case "find": {
                return executeFindCommand(arrOfStr);
            }
            case "help": {
                return executeHelpCommand();
            }
            default:
                // add to list
                if ((identifier.equals("todo") || identifier.equals("deadline")
                        || identifier.equals("event")) && arrOfStr.length < 2) {
                    return executeEmptyDescriptionCommand(identifier);
                } else {
                    switch (identifier) {
                        case "todo":
                            return executeTodoCommand(input);
                        case "deadline": {
                            return executeDeadlineCommand(input);
                        }
                        case "event": {
                            return executeEventCommand(input);
                        }
                        default:
                            return executeWrongInputCommand();
                    }
                }
        }
    }
}
