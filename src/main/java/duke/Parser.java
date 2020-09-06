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

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String FIND = "find";

    private static final int INDEX_OF_DESCRIPTION_START_FOR_TODO = 5;
    private static final int INDEX_OF_DESCRIPTION_START_FOR_EVENT = 6;
    private static final int INDEX_OF_DESCRIPTION_START_FOR_DEADLINE = 9;
    private static final int INDEX_OF_DESCRIPTION_START_FOR_FIND = 5;

    /**
     * Parses user commands and returns the appropriate executable command for the program to then execute.
     *
     * @param fullCommand The user command to be parsed.
     * @return The appropriate command to be executed.
     */
    public static Command parse(String fullCommand) throws WrongFormatException {
        String[] commandWords = fullCommand.split(" ");
        String firstWord = commandWords[0];
        if (fullCommand.equals(BYE)) { // Exit the program
            return new ExitCommand();
        } else if (fullCommand.equals(LIST)) { // List out task list
            return new ListCommand();
        } else if (firstWord.equals(DONE)) { // Done with a task
            return createDoneCommand(commandWords);
        } else if (firstWord.equals(DELETE)) { // Delete a task
            return createDeleteCommand(commandWords);
        } else if (firstWord.equals(TODO)) { // Add To-Do task
            return createAddTodoCommand(fullCommand);
        } else if (firstWord.equals(EVENT)) { // Add duke.task.Event task
            return createAddEventCommand(fullCommand);
        } else if (firstWord.equals(DEADLINE)) { // Add duke.task.Deadline task
            return createAddDeadlineCommand(fullCommand);
        } else if (firstWord.equals(FIND)) { // Find task(s) in task list
            return createFindCommand(fullCommand);
        } else { // Unknown command entered
            return new UnknownCommand();
        }
    }

    private static Command createDoneCommand(String[] commandWords) throws DoneWrongFormatException {
        try {
            if (commandWords.length != 2) { // If command is in a wrong format by having more than 2 words
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
            if (commandWords.length != 2) { // If command is in a wrong format by having more than 2 words
                throw new DeleteWrongFormatException();
            }
            int taskIndex = Integer.parseInt(commandWords[1]) - 1; // Index of task in the task list
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) { // Second argument of command was not a number, e.g. "delete X"
            throw new DeleteWrongFormatException();
        }
    }

    private static Command createAddTodoCommand(String fullCommand) throws TodoWrongFormatException {
        try {
            String taskDescription = fullCommand.substring(INDEX_OF_DESCRIPTION_START_FOR_TODO).trim();
            if (taskDescription.isEmpty()) {
                throw new TodoWrongFormatException();
            }
            return new AddTodoCommand(taskDescription);
        } catch (IndexOutOfBoundsException e) { // add to-do command is in a wrong format
            throw new TodoWrongFormatException();
        }
    }

    private static Command createAddEventCommand(String fullCommand) throws EventWrongFormatException {
        try {
            String[] commandParts = fullCommand.split("/at");
            String taskDescription = commandParts[0].substring(INDEX_OF_DESCRIPTION_START_FOR_EVENT).trim();
            if (taskDescription.isEmpty()) {
                throw new EventWrongFormatException();
            }
            String taskLocation = commandParts[1].trim();
            return new AddEventCommand(taskDescription, taskLocation);
        } catch (IndexOutOfBoundsException e) { // add event command is in a wrong format
            throw new EventWrongFormatException();
        }
    }

    private static Command createAddDeadlineCommand(String fullCommand) throws DeadlineWrongFormatException {
        try {
            String[] commandParts = fullCommand.split("/by");
            String taskDescription = commandParts[0].substring(INDEX_OF_DESCRIPTION_START_FOR_DEADLINE).trim();
            if (taskDescription.isEmpty()) {
                throw new DeadlineWrongFormatException();
            }
            String taskDeadline = commandParts[1].trim();

            String[] dateAndTimeParts = taskDeadline.split(" ");
            String deadlineDateString = dateAndTimeParts[0];
            String deadlineTimeString = dateAndTimeParts[1];
            int deadlineTimeHours = Integer.parseInt(deadlineTimeString.substring(0, 2));
            int deadlineTimeMinutes = Integer.parseInt(deadlineTimeString.substring(2, 4));
            LocalDate deadlineDate = LocalDate.parse(deadlineDateString);
            LocalDateTime deadlineDateAndTime = deadlineDate.atTime(deadlineTimeHours, deadlineTimeMinutes);

            return new AddDeadlineCommand(taskDescription, deadlineDate, deadlineDateAndTime);
        } catch (IndexOutOfBoundsException | DateTimeException | NumberFormatException e) {
            // add deadline command is in a wrong format
            throw new DeadlineWrongFormatException();
        }
    }

    private static Command createFindCommand(String fullCommand) throws FindWrongFormatException {
        try {
            String keyWords = fullCommand.substring(INDEX_OF_DESCRIPTION_START_FOR_FIND).trim();
            if (keyWords.isEmpty()) {
                throw new FindWrongFormatException();
            }
            return new FindCommand(keyWords);
        } catch (IndexOutOfBoundsException e) {
            throw new FindWrongFormatException();
        }
    }
}
