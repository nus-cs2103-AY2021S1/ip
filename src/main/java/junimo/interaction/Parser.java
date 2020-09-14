package junimo.interaction;

import junimo.task.Deadline;
import junimo.task.Event;
import junimo.task.Task;
import junimo.task.TaskList;
import junimo.task.Todo;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Deals with parsing and making sense of user commands.
 */
public class Parser {
    private final TaskList taskList;

    private enum TaskInputPattern {
        CORRECT_TODO("todo (\\S+\\s?)+"),
        BLANK_TODO("todo\\p{Blank}?"),
        CORRECT_DEADLINE("deadline (\\S+\\s?)+ /by (\\S+\\s?)+"),
        BLANK_DEADLINE_DATE("deadline.+/by\\p{Blank}?"),
        BLANK_DEADLINE_DESC("deadline\\p{Blank}+/by.?"),
        CORRECT_EVENT("event (\\S+\\s?)+ /at (\\S+\\s?)+"),
        BLANK_EVENT_LOC("event.+/at\\p{Blank}?"),
        BLANK_EVENT_DESC("event\\p{Blank}+/at.?");

        private final String pattern;

        TaskInputPattern(String pattern) {
            this.pattern = pattern;
        }
    }

    /**
     * Constructs an instance of Parser that adds, retrieves and delete tasks from taskList.
     * @param taskList taskList that contains and handles the user's list of Tasks.
     */
    public Parser(TaskList taskList, BufferedReader br) {
        this.taskList = taskList;
        try {
            parseFile(br);
        } catch (IOException e) {
            System.out.println("Error populating task list with saved tasks: " + e);
        }
    }

    private void parseFile(BufferedReader br) throws IOException {
        String line = br.readLine();
        boolean isFileCorrupted = false;
        boolean isArchive = false;
        while (line != null) {
            if (line.equals("archives")) {
                isArchive = true;
                line = br.readLine();
                continue;
            }
            try {
                boolean isDone = Boolean.parseBoolean(br.readLine());
                parseAddTaskCommand(line, isDone, false, isArchive);
            } catch (IllegalArgumentException e) {
                if (!isFileCorrupted) {
                    String errMessage = "WARNING: Your stored data appears to be in a corrupted format. "
                            + "Some tasks may be lost.";
                    System.out.println(errMessage);
                    isFileCorrupted = true;
                }
            } finally {
                line = br.readLine();
            }
        }
    }

    /**
     * Parses user input commands and handles them appropriately.
     * Feedbacks to user if commands are not understandable/ in the wrong format.
     *
     * Handles the following command types: bye, done, delete, todo, deadline and event.
     */
    public String parseInputCommand(String inputCommand) {
        String[] splitCommand = inputCommand.split(" ", 2);
        assert splitCommand.length > 0 : "inputCommand should not be empty.";

        String command = splitCommand[0];

        switch (command) {
        case "bye":
            if (splitCommand.length == 1) {
                return Greeting.exit();
            }
            // Fallthrough if length of splitCommand > 1

        case "list":
            if (splitCommand.length == 1) {
                return taskList.list();
            }
            // Fallthrough if length of splitCommand > 1

        case "archives":
            if (splitCommand.length == 1) {
                return taskList.showArchives();
            }
            // Fallthrough if length of splitCommand > 1

        case "done":
            try {
                return taskList.markTaskAsDone(splitCommand[1]);
            } catch (IndexOutOfBoundsException ex) {
                String errMessage = "Please indicate which task you'd like to check off!";
                System.out.println(errMessage);
                return errMessage;
            }

        case "archive":
            try {
                return taskList.archive(splitCommand[1]);
            } catch (IndexOutOfBoundsException ex) {
                String errMessage = "Please indicate which task you'd like to archive!";
                System.out.println(errMessage);
                return errMessage;
            }

        case "unarchive":
            try {
                return taskList.unarchive(splitCommand[1]);
            } catch (IndexOutOfBoundsException ex) {
                String errMessage = "Please indicate which task you'd like to unarchive!";
                System.out.println(errMessage);
                return errMessage;
            }

        case "delete":
            try {
                return taskList.deleteTask(splitCommand[1]);
            } catch (IndexOutOfBoundsException ex) {
                String errMessage = "Please indicate which task you'd like to delete!";
                System.out.println(errMessage);
                return errMessage;
            }

        case "find":
            if (splitCommand.length == 1) {
                return taskList.find("");
            } else {
                return taskList.find(splitCommand[1]);
            }

        case "todo":
        case "deadline":
        case "event":
            try {
                return parseAddTaskCommand(inputCommand, false, true, false);
            } catch (IllegalArgumentException ex) {
                String errMessage = ex.getMessage();
                System.out.println(errMessage);
                return errMessage;
            }

        default:
            String errMessage = "Sorry I don't know what that means!\n";
            System.out.println(errMessage);
            return errMessage;
        }
    }

    private String parseAddTaskCommand(String input, boolean isDone, boolean shouldEcho, boolean isArchive) {
        String[] splitInput = input.split(" ", 2);
        String taskType = splitInput[0];

        switch(taskType) {
        case "todo":
            if (input.matches(TaskInputPattern.CORRECT_TODO.pattern)) {
                Task newTask = new Todo(splitInput[1], isDone);
                return isArchive
                        ? taskList.addArchivedTask(newTask)
                        : taskList.addTask(newTask, shouldEcho);
            } else if (input.matches(TaskInputPattern.BLANK_TODO.pattern)) {
                throwEmptyFieldException("todo", "description");
            } else {
                throwInvalidTaskSyntaxException("todo");
            }
            break;

        case "deadline":
            if (input.matches(TaskInputPattern.CORRECT_DEADLINE.pattern)) {
                String[] splitDeadline = splitInput[1].split(" /by ");
                String deadlineDesc = splitDeadline[0];
                String by = splitDeadline[1];
                Task newTask = new Deadline(deadlineDesc, by, isDone);
                return isArchive
                        ? taskList.addArchivedTask(newTask)
                        : taskList.addTask(newTask, shouldEcho);
            } else if (input.matches(TaskInputPattern.BLANK_DEADLINE_DESC.pattern)
                    || input.matches(TaskInputPattern.BLANK_DEADLINE_DATE.pattern)) {
                throwEmptyFieldException("deadline", "description", "date");
            } else {
                throwInvalidTaskSyntaxException("deadline");
            }
            break;

        case "event":
            if (input.matches(TaskInputPattern.CORRECT_EVENT.pattern)) {
                String[] splitEvent = splitInput[1].split(" /at ");
                String eventDesc = splitEvent[0];
                String at = splitEvent[1];
                Task newTask = new Event(eventDesc, at, isDone);
                return isArchive
                        ? taskList.addArchivedTask(newTask)
                        : taskList.addTask(newTask, shouldEcho);
            } else if (input.matches(TaskInputPattern.BLANK_EVENT_DESC.pattern)
                    || input.matches(TaskInputPattern.BLANK_EVENT_LOC.pattern)) {
                throwEmptyFieldException("event", "description", "location");
            } else {
                throwInvalidTaskSyntaxException("event");
            }
            break;

        default:
            throw new IllegalArgumentException("OOPS! There is no task of type " + taskType + "!");
        }
        assert false;
        return null;
    }

    private void throwEmptyFieldException(String taskType, String ... fields) {
        StringBuilder emptyFields = new StringBuilder();
        boolean isFirstField = true;
        for (String field: fields) {
            if (isFirstField) {
                emptyFields.append(field);
                isFirstField = false;
            } else {
                emptyFields.append("/").append(field);
            }
        }
        throw new IllegalArgumentException("OOPS! The " + emptyFields.toString() + " of " + taskType
                + " cannot be empty.");
    }

    private void throwInvalidTaskSyntaxException(String taskType) {
        throw new IllegalArgumentException("OOPS! Invalid syntax. To add a " + taskType + ", use:\n"
                + Task.getFormat(taskType));
    }
}
