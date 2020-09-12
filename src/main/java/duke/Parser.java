package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import exception.InvalidCommandException;
import exception.MissingInfoException;

import task.Task;
import task.TaskDateTimeComparator;

/**
 * Parser deals with making sense of the user command.
 */
public class Parser {

    private Scanner input;

    private static final String LIST_REPLY = "Here are the tasks in your list:\n";
    private static final String DONE_REPLY = "This task has been marked as done:\n";
    private static final String INVALID_TASK_NUMBER = "OOPS!!! Task number is invalid.";
    private static final String NOT_A_NUMBER = "OOPS!!! Task number must be a number.";
    private static final String EMPTY_KEYWORD = "OOPS!!! Keyword cannot be empty.";
    private static final String FIND_REPLY = "Here are the matching tasks in your list:\n";
    private static final String INVALID_DATE = "OOPS!!! Date format is invalid. Make sure it is yyyy-mm-ddTHH:mm.";
    private static final String SORT_REPLY = "Here are the tasks sorted by date and time:\n";

    public Parser(Scanner input) {
        this.input = input;
    }

    /**
     * Sets the scanner input stored in this object.
     *
     * @param scanner The scanner to store.
     */
    public void setScanner(Scanner scanner) {
        this.input = scanner;
    }

    /**
     * Takes in the command entered by the user and execute it accordingly.
     *
     * @param taskList All the tasks in the to-do list.
     * @return String The relevant reply according to command.
     */
    public String executeCommand(TaskList taskList) {
        String command = this.input.next();

        if (command.equals("bye")) {
            return "bye";
        } else if (command.equals("list")) {
            return LIST_REPLY + taskList.listTasks();
        } else if (command.equals("done")) {
            return executeDone(taskList);
        } else if (command.equals("delete")) {
            return executeDelete(taskList);
        } else if (command.equals("find")) {
            return executeFind(taskList);
        } else if (command.equals("sort")) {
            return executeSort(taskList);
        } else {
            return executeNewTask(taskList, command);
        }
    }

    private String executeDone(TaskList taskList) {
        try {
            Task task = taskList.getTasks().get(this.input.nextInt() - 1);
            task.completeTask();
            return DONE_REPLY + task.toString();
        } catch (IndexOutOfBoundsException e) {
            return INVALID_TASK_NUMBER;
        } catch (NoSuchElementException e) {
            clearInputLine();
            return NOT_A_NUMBER;
        }
    }

    private String executeDelete(TaskList taskList) {
        try {
            int taskNumber = this.input.nextInt();
            Task task = taskList.getTasks().get(taskNumber - 1);
            taskList.removeTask(taskNumber - 1);
            return concatStrings("This task has been removed:\n", task.toString(),
                    "\nNow you have " + taskList.getTasks().size(), " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            return INVALID_TASK_NUMBER;
        } catch (NoSuchElementException e) {
            clearInputLine();
            return NOT_A_NUMBER;
        }
    }

    private String executeFind(TaskList taskList) {
        String keyword;
        try {
            keyword = input.nextLine().substring(1);
        } catch (StringIndexOutOfBoundsException | NoSuchElementException e) {
            return EMPTY_KEYWORD;
        }
        TaskList foundTasks = new TaskList(taskList.findTasks(keyword));
        return FIND_REPLY + foundTasks.listTasks();
    }

    private String executeNewTask(TaskList taskList, String command) {
        try {
            TaskType.TypeOfTask typeOfTask = getTypeOfTask(command);

            Task newTask = getTask(command, typeOfTask, taskList);
            taskList.addTask(newTask);
            return concatStrings("Got it. I've added this task:\n", newTask.toString(),
                    "\nNow you have " + taskList.getTasks().size(), " tasks in the list.");
        } catch (InvalidCommandException e) {
            return e.getMessage();
        } catch (MissingInfoException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return INVALID_DATE;
        }
    }

    private String concatStrings(String ... words) {
        String concatenatedString = "";

        for (int i = 0; i < words.length; i++) {
            concatenatedString = concatenatedString + words[i];
        }

        return concatenatedString;
    }

    private TaskType.TypeOfTask getTypeOfTask(String command) throws InvalidCommandException {
        switch (command) {
            case "todo":
                return TaskType.TypeOfTask.TODO;
            case "deadline":
                return TaskType.TypeOfTask.DEADLINE;
            case "event":
                return TaskType.TypeOfTask.EVENT;
            default:
                clearInputLine();
                throw new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void clearInputLine() {
        if (this.input.hasNextLine()) {
            this.input.nextLine();
        }
    }

    private Task getTask(String command, TaskType.TypeOfTask typeOfTask, TaskList taskList)
            throws MissingInfoException {
        String[] commandArray;
        try {
            commandArray = this.input.nextLine().split(" ");
        } catch (NoSuchElementException e) {
            throw new MissingInfoException("OOPS!!! The description of a " + command + " cannot be empty.");
        }
        String description = "";
        LocalDateTime timing = null;

        for (int i = 1; i < commandArray.length; i++) {
            if (commandArray[i].equals("/by")) {
                timing = getTiming(command, commandArray, i + 1);
                break;
            } else if (commandArray[i].equals("/at")) {
                timing = getTiming(command, commandArray, i + 1);
                break;
            }

            if (i == 1) {
                description = commandArray[i];
            } else {
                description = description + " " + commandArray[i];
            }
        }

        if (description.isEmpty()) {
            throw new MissingInfoException("OOPS!!! The description of a " + command + " cannot be empty.");
        }

        if ((typeOfTask.equals(TaskType.TypeOfTask.DEADLINE) || typeOfTask.equals(TaskType.TypeOfTask.EVENT))
                && timing == null) {
            throw new MissingInfoException("OOPS!!! The date/time of a " + command + " cannot be empty.");
        }

        return taskList.createTask(typeOfTask, description, timing, false);
    }

    private LocalDateTime getTiming(String command, String[] commandArray, int index)
            throws MissingInfoException, DateTimeParseException {
        String timing = "";

        for (int i = index; i < commandArray.length; i++) {
            if (i == index) {
                timing = commandArray[i];
            } else {
                timing = timing + " " + commandArray[i];
            }
        }

        if (timing.isEmpty()) {
            throw new MissingInfoException("OOPS!!! The date/time of a " + command + " cannot be empty.");
        }

        try {
            return LocalDateTime.parse(timing);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    private String executeSort(TaskList taskList) {
        ArrayList<Task> copyOfTaskList = new ArrayList<>(taskList.getTasks());
        TaskList sortedTasks = new TaskList(copyOfTaskList);
        sortedTasks.getTasks().sort(new TaskDateTimeComparator());
        return SORT_REPLY + sortedTasks.listTasks();
    }
}
