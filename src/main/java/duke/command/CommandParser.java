package duke.command;

import duke.exception.DateParseException;
import duke.exception.IncompleteTaskException;
import duke.exception.InvalidTaskException;
import duke.exception.UnknownCommandException;
import duke.task.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandParser {
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

    private boolean validAddTaskCommand(String command) {
        return command.split(" ")[0].equals("todo") || command.split(" ")[0].equals("deadline") ||
                command.split(" ")[0].equals("event");
    }

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
