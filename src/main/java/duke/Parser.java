package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.exception.DeadlineWrongFormatException;
import duke.exception.DeleteWrongFormatException;
import duke.exception.DoneWrongFormatException;
import duke.exception.EventWrongFormatException;
import duke.exception.FindWrongFormatException;
import duke.exception.TodoWrongFormatException;
import duke.exception.WrongFormatException;

/**
 * Parses user commands in the Duke program.
 */
public class Parser {

    private static final int INDEX_OF_HOURS_START = 0;
    private static final int INDEX_OF_HOURS_END = 2;
    private static final int INDEX_OF_MINUTES_START = 2;
    private static final int INDEX_OF_MINUTES_END = 4;

    /**
     * Parses user commands and returns the appropriate executable command for the program to then execute.
     *
     * @param fullCommand The user command to be parsed.
     * @return The appropriate command to be executed.
     * @throws WrongFormatException If the user command is wrongly formatted.
     */
    public static Command parse(String fullCommand) throws WrongFormatException {
        String[] commandWords = fullCommand.split(" ");
        String firstWord = commandWords[0];
        if (isExitCommand(fullCommand)) { // Exit the program
            return new ExitCommand();
        } else if (isListCommand(fullCommand)) { // List out task list
            return new ListCommand();
        } else if (isDoneCommand(firstWord)) { // Done with a task
            return createDoneCommand(commandWords);
        } else if (isDeleteCommand(firstWord)) { // Delete a task
            return createDeleteCommand(commandWords);
        } else if (isAddTodoCommand(firstWord)) { // Add To-Do task
            return createAddTodoCommand(fullCommand, firstWord);
        } else if (isAddEventCommand(firstWord)) { // Add Event task
            return createAddEventCommand(fullCommand, firstWord);
        } else if (isAddDeadlineCommand(firstWord)) { // Add Deadline task
            return createAddDeadlineCommand(fullCommand, firstWord);
        } else if (isFindCommand(firstWord)) { // Find task(s) in task list
            return createFindCommand(fullCommand, firstWord);
        } else { // Unknown command entered
            return new UnknownCommand();
        }
    }

    private static boolean isExitCommand(String fullCommand) {
        return ExitCommand.COMMAND_WORDS.contains(fullCommand.toLowerCase());
    }

    private static boolean isListCommand(String fullCommand) {
        return ListCommand.COMMAND_WORDS.contains(fullCommand.toLowerCase());
    }

    private static boolean isDoneCommand(String firstWord) {
        return DoneCommand.COMMAND_WORDS.contains(firstWord.toLowerCase());
    }

    private static boolean isDeleteCommand(String firstWord) {
        return DeleteCommand.COMMAND_WORDS.contains(firstWord.toLowerCase());
    }

    private static boolean isAddTodoCommand(String firstWord) {
        return AddTodoCommand.COMMAND_WORDS.contains(firstWord.toLowerCase());
    }

    private static boolean isAddEventCommand(String firstWord) {
        return AddEventCommand.COMMAND_WORDS.contains(firstWord.toLowerCase());
    }

    private static boolean isAddDeadlineCommand(String firstWord) {
        return AddDeadlineCommand.COMMAND_WORDS.contains(firstWord.toLowerCase());
    }

    private static boolean isFindCommand(String firstWord) {
        return FindCommand.COMMAND_WORDS.contains(firstWord.toLowerCase());
    }

    private static Command createDoneCommand(String[] commandWords) throws DoneWrongFormatException {
        try {
            if (commandWords.length != DoneCommand.EXPECTED_NUMBER_OF_ARGUMENTS) { // If command is in a wrong format
                // by having more than 2 words
                throw new DoneWrongFormatException();
            }
            int taskIndex = Integer.parseInt(commandWords[1]) - 1; // Index of task in the task list
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException e) { // Second argument of command was not a number, e.g. "done X"
            throw new DoneWrongFormatException();
        }
    }

    private static Command createDeleteCommand(String[] commandWords) throws DeleteWrongFormatException {
        try {
            if (commandWords.length != DeleteCommand.EXPECTED_NUMBER_OF_ARGUMENTS) { // If command is in a wrong format
                // by having more than 2 words
                throw new DeleteWrongFormatException();
            }
            int taskIndex = Integer.parseInt(commandWords[1]) - 1; // Index of task in the task list
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) { // Second argument of command was not a number, e.g. "delete X"
            throw new DeleteWrongFormatException();
        }
    }

    private static Command createAddTodoCommand(String fullCommand, String firstWord) throws TodoWrongFormatException {
        try {
            int indexOfStartOfDescription = firstWord.length() + 1;
            String taskDescription = fullCommand.substring(indexOfStartOfDescription).trim();
            if (taskDescription.isEmpty()) {
                throw new TodoWrongFormatException();
            }
            return new AddTodoCommand(taskDescription);
        } catch (IndexOutOfBoundsException e) { // add to-do command is in a wrong format
            throw new TodoWrongFormatException();
        }
    }

    private static Command createAddEventCommand(String fullCommand, String firstWord)
            throws EventWrongFormatException {
        try {
            int indexOfStartOfDescription = firstWord.length() + 1;
            String[] commandParts = fullCommand.split(AddEventCommand.COMMAND_SPLIT_WORD);
            String taskDescription = commandParts[0].substring(indexOfStartOfDescription).trim();
            if (taskDescription.isEmpty()) {
                throw new EventWrongFormatException();
            }
            String taskLocation = commandParts[1].trim();
            if (taskLocation.isEmpty()) {
                throw new EventWrongFormatException();
            }
            return new AddEventCommand(taskDescription, taskLocation);
        } catch (IndexOutOfBoundsException e) { // add event command is in a wrong format
            throw new EventWrongFormatException();
        }
    }

    private static Command createAddDeadlineCommand(String fullCommand, String firstWord)
            throws DeadlineWrongFormatException {
        try {
            int indexOfStartOfDescription = firstWord.length() + 1;
            String[] commandParts = fullCommand.split(AddDeadlineCommand.COMMAND_SPLIT_WORD);
            String taskDescription = commandParts[0].substring(indexOfStartOfDescription).trim();
            if (taskDescription.isEmpty()) {
                throw new DeadlineWrongFormatException();
            }
            String taskDeadline = commandParts[1].trim();

            String[] dateAndTimeParts = taskDeadline.split(" ");
            String deadlineDateString = dateAndTimeParts[0];
            String deadlineTimeString = dateAndTimeParts[1];
            int deadlineTimeHours = Integer.parseInt(deadlineTimeString.substring(INDEX_OF_HOURS_START,
                    INDEX_OF_HOURS_END));
            int deadlineTimeMinutes = Integer.parseInt(deadlineTimeString.substring(INDEX_OF_MINUTES_START,
                    INDEX_OF_MINUTES_END));
            LocalDate deadlineDate = LocalDate.parse(deadlineDateString);
            LocalDateTime deadlineDateAndTime = deadlineDate.atTime(deadlineTimeHours, deadlineTimeMinutes);

            return new AddDeadlineCommand(taskDescription, deadlineDate, deadlineDateAndTime);
        } catch (IndexOutOfBoundsException | DateTimeException | NumberFormatException e) {
            // add deadline command is in a wrong format
            throw new DeadlineWrongFormatException();
        }
    }

    private static Command createFindCommand(String fullCommand, String firstWord) throws FindWrongFormatException {
        try {
            int indexOfStartOfDescription = firstWord.length() + 1;
            String keyWords = fullCommand.substring(indexOfStartOfDescription).trim();
            if (keyWords.isEmpty()) {
                throw new FindWrongFormatException();
            }
            return new FindCommand(keyWords);
        } catch (IndexOutOfBoundsException e) {
            throw new FindWrongFormatException();
        }
    }
}
