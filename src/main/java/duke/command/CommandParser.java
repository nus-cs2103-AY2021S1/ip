package duke.command;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import duke.task.TaskType;

import duke.exception.DateParseException;
import duke.exception.IncompleteTaskException;
import duke.exception.InvalidTaskException;
import duke.exception.UnknownCommandException;

/**
 * CommandParser is a class that parses input from the user, and returns a relevant command.
 */
public class CommandParser {
    /**
     * Processes String value commands given by the user and returns the relevant command.
     * @param command A String value which is the user input.
     * @return A Command corresponding to user input.
     * @throws UnknownCommandException if the Command type cannot be determined from user input.
     * @throws DateParseException if the user input contains a date object that cannot be parsed.
     * @throws IncompleteTaskException if user input contains incorrect or insufficient information for task creation.
     * @throws InvalidTaskException if task index provided by user is invalid or missing.
     */
    public Command parseCommand(String command) throws UnknownCommandException, DateParseException,
            IncompleteTaskException, InvalidTaskException {
        command = command.trim();
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("today")) {
            return new TodayCommand();
        } else if (command.length() >= 4 && command.substring(0, 4).equals("done")) {
            return parseDoneCommand(command);
        } else if (command.length() >= 6 && command.substring(0, 6).equals("delete")) {
            return parseDeleteCommand(command);
        } else if (validAddTaskCommand(command)) {
            return parseAddTaskCommand(command);
        } else {
            throw new UnknownCommandException("Oh noes! I'm not sure what that means ;A;");
        }
    }

    /**
     * Checks if the user input string constitutes a valid command to add a task.
     * @param command A String value which is the user input.
     * @return A boolean value that indicates whether the user input string constitutes a valid command to add a task.
     */
    private boolean validAddTaskCommand(String command) {
        return command.split(" ")[0].equals("todo") || command.split(" ")[0].equals("deadline") ||
                command.split(" ")[0].equals("event");
    }

    /**
     * Parses user input to create a DoneCommand.
     * @param command A String value which is the user input.
     * @return A DoneCommand that when executed, marks a specified task as completed.
     * @throws InvalidTaskException if task index provided by user is invalid or missing.
     */
    private DoneCommand parseDoneCommand(String command) throws InvalidTaskException {
        if (command.length() < 5) {
            throw new InvalidTaskException("Oh noes! I don't think you specified a valid task index :<");
        }
        try {
            int index = Integer.parseInt(command.substring(5));
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Oh noes! I don't think you specified a valid task index :<");
        }
    }

    /**
     * Parses user input to create a DeleteCommand.
     * @param command A String value which is the user input.
     * @return A DeleteCommand that when executed, deletes a specified task.
     * @throws InvalidTaskException if task index provided by user is invalid or missing.
     */
    private DeleteCommand parseDeleteCommand(String command) throws InvalidTaskException {
        if (command.length() < 7) {
            throw new InvalidTaskException("Oh noes! I don't think you specified a valid task index :<");
        }
        try {
            int index = Integer.parseInt(command.substring(7));
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Oh noes! I don't think you specified a valid task index :<");
        }
    }

    /**
     * Parses user input to create a AddTaskCommand.
     * @param command A String value which is the user input.
     * @return A AddTaskCommand that when executed, adds a Task with the specified details.
     * @throws IncompleteTaskException if user input contains incorrect or insufficient information for task creation.
     * @throws UnknownCommandException if the Task type cannot be determined from user input.
     * @throws DateParseException if the user input contains a date object that cannot be parsed.
     */
    private AddTaskCommand parseAddTaskCommand(String command) throws IncompleteTaskException,
            UnknownCommandException, DateParseException {
        try {
            String typeOfTask = command.split(" ")[0];
            switch (typeOfTask) {
            case "todo":
                if (command.length() <= 4) {
                    throw new IncompleteTaskException("Oh dear! Your task description seems to be incomplete :<");
                }
                return new AddTaskCommand(TaskType.TODO, command.substring(5));
            case "event":
                if (!command.contains("/at") || (command.indexOf("event ") + 6 > command.indexOf(" /at"))) {
                    throw new IncompleteTaskException("Oh dear! Your task description seems to be incomplete :<");
                }
                String eventName = command.substring(command.indexOf("event ") + 6, command.indexOf(" /at"));
                String eventDate = command.substring(command.indexOf("/at ") + 4);

                return new AddTaskCommand(TaskType.EVENT, eventName, LocalDate.parse(eventDate));
            case "deadline":
                if (!command.contains("/by") || (command.indexOf("deadline ") + 9 > command.indexOf(" /by"))) {
                    throw new IncompleteTaskException("Oh dear! Your task description seems to be incomplete :<");
                }
                String deadlineName = command.substring(command.indexOf("deadline ") + 9, command.indexOf(" /by"));
                String deadlineDate = command.substring(command.indexOf("/by ") + 4);
                return new AddTaskCommand(TaskType.DEADLINE, deadlineName, LocalDate.parse(deadlineDate));
            }
            throw new UnknownCommandException("Oh noes! I'm not sure what that means ;A;");
        } catch (DateTimeParseException e) {
            throw new DateParseException("Oops! Please make sure your date is of YYYY-MM-DD format ;A;");
        }
    }
}
