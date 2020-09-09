package duke;

import java.util.Arrays;
import java.util.List;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.PriorityCommand;
import duke.exception.EmptyTaskDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDeadlineCommandException;
import duke.exception.InvalidEventCommandException;
import duke.exception.InvalidFindCommandException;
import duke.exception.InvalidPriorityCommandException;
import duke.exception.InvalidPriorityLevel;
import duke.exception.InvalidTaskNumberCommandException;
import duke.exception.InvalidTaskNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a parser for parsing the command.
 *
 * @author Tee Kok Siang
 */
public class Parser {
    private static String combineWords(List<String> words) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            if (i == 0) {
                message.append(words.get(i));
            } else {
                message.append((" ".concat(words.get(i))));
            }
        }
        return message.toString();
    }

    private static int extractTaskNumber(String command) throws InvalidTaskNumberCommandException,
            InvalidTaskNumberException {
        List<String> commandWords = Arrays.asList(command.split(Command.SPLIT_DELIMITER));
        if (commandWords.size() < Command.TASK_NUMBER_COMMAND_WORD_COUNT) {
            throw new InvalidTaskNumberCommandException();
        } else {
            // convert the task number into int
            try {
                return Integer.parseInt(commandWords.get(Command.TASK_NUMBER_POSITION));
            } catch (NumberFormatException numberFormatException) {
                throw new InvalidTaskNumberException();
            }
        }
    }

    private static int extractTaskPriorityLevel(String command) throws InvalidPriorityCommandException,
            InvalidPriorityLevel {
        List<String> commandWords = Arrays.asList(command.split(Command.SPLIT_DELIMITER));
        if (commandWords.size() != PriorityCommand.WORD_COUNT) {
            throw new InvalidPriorityCommandException();
        } else {
            // convert the task number into int
            try {
                return Integer.parseInt(commandWords.get(PriorityCommand.PRIORITY_LEVEL_POSITION));
            } catch (NumberFormatException numberFormatException) {
                throw new InvalidPriorityLevel();
            }
        }
    }

    private static Task parseAddCommand(String command) throws EmptyTaskDescriptionException,
            InvalidDeadlineCommandException, InvalidEventCommandException, InvalidCommandException {
        List<String> commandWords = Arrays.asList(command.split(Command.SPLIT_DELIMITER));
        String taskType = commandWords.get(0);
        Task task;
        String taskDescription;
        if (commandWords.size() < AddCommand.MIN_WORD_COUNT) {
            throw new EmptyTaskDescriptionException();
        }
        if (taskType.equalsIgnoreCase(Command.TODO_COMMAND)) {
            taskDescription = combineWords(commandWords.subList(1, commandWords.size()));
            task = new Todo(taskDescription);
        } else if (taskType.equalsIgnoreCase(Command.DEADLINE_COMMAND)) {
            int index = commandWords.indexOf(Deadline.BY_KEYWORD);
            if (index == Deadline.INVALID_BY_POSITION) {
                throw new EmptyTaskDescriptionException();
            }
            boolean hasByKeyword = index != -1;
            boolean hasDate = commandWords.size() - index > 1;
            if (!hasByKeyword || !hasDate) {
                throw new InvalidDeadlineCommandException();
            }
            taskDescription = combineWords(commandWords.subList(1, index));
            String by = combineWords(commandWords.subList(index + 1, commandWords.size()));
            boolean isDateValid = by.matches(Task.DATE_FORMAT);
            if (!isDateValid) {
                throw new InvalidDeadlineCommandException();
            }
            task = new Deadline(taskDescription, by);
        } else if (taskType.equalsIgnoreCase(Command.EVENT_COMMAND)) {
            int index = commandWords.indexOf(Event.AT_KEYWORD);
            if (index == Event.INVALID_AT_POSITION) {
                throw new EmptyTaskDescriptionException();
            }
            boolean hasAtKeyword = index != -1;
            boolean hasDate = commandWords.size() - index > 1;
            if (!hasAtKeyword || !hasDate) {
                throw new InvalidEventCommandException();
            }
            taskDescription = combineWords(commandWords.subList(1, index));
            String at = combineWords(commandWords.subList(index + 1, commandWords.size()));
            task = new Event(taskDescription, at);
        } else {
            throw new InvalidCommandException();
        }
        return task;
    }

    /**
     * Parses the input to a {@link Command}.
     * Extracts the keyword from the input and return a {@link Command}.
     *
     * @param fullCommand The input from the user to be parsed to a {@link Command}.
     * @return Command which is parsed from the input.
     */
    public static Command parse(String fullCommand) throws InvalidPriorityCommandException,
            InvalidTaskNumberException, InvalidTaskNumberCommandException, InvalidPriorityLevel,
            InvalidCommandException, InvalidDeadlineCommandException, InvalidEventCommandException,
            EmptyTaskDescriptionException, InvalidFindCommandException {
        boolean isTodoCommand = fullCommand.startsWith(AddCommand.TODO_COMMAND);
        boolean isDeadlineCommand = fullCommand.startsWith(AddCommand.DEADLINE_COMMAND);
        boolean isEventCommand = fullCommand.startsWith(AddCommand.EVENT_COMMAND);
        boolean isAddCommand = isTodoCommand || isDeadlineCommand || isEventCommand;

        if (fullCommand.equalsIgnoreCase(Command.BYE_COMMAND)) {
            return new ByeCommand();
        } else if (fullCommand.equalsIgnoreCase(Command.LIST_COMMAND)) {
            return new ListCommand();
        } else if (fullCommand.startsWith(Command.DONE_COMMAND)) {
            int taskNumber = extractTaskNumber(fullCommand);
            return new DoneCommand(taskNumber);
        } else if (fullCommand.startsWith(Command.DELETE_COMMAND)) {
            int taskNumber = extractTaskNumber(fullCommand);
            return new DeleteCommand(taskNumber);
        } else if (fullCommand.startsWith(Command.FIND_COMMAND)) {
            if (fullCommand.length() < FindCommand.MIN_WORD_COUNT) {
                throw new InvalidFindCommandException();
            }
            String query = fullCommand.substring(FindCommand.QUERY_START_POSITION);
            return new FindCommand(query);
        } else if (isAddCommand) {
            Task newTask = parseAddCommand(fullCommand);
            return new AddCommand(newTask);
        } else if (fullCommand.startsWith(Command.PRIORITY_COMMAND)) {
            int taskNumber = extractTaskNumber(fullCommand);
            int priorityLevel = extractTaskPriorityLevel(fullCommand);
            return new PriorityCommand(taskNumber, priorityLevel);
        } else {
            throw new InvalidCommandException();
        }
    }
}
