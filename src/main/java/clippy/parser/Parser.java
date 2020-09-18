package clippy.parser;

import clippy.command.Command;
import clippy.command.ExitCommand;
import clippy.command.ListCommand;
import clippy.command.HelpCommand;
import clippy.command.DeleteCommand;
import clippy.command.DoneCommand;
import clippy.command.FindCommand;
import clippy.command.UpdateTimeCommand;
import clippy.command.UpdateDescriptionAndTimeCommand;
import clippy.command.UpdateDescriptionCommand;
import clippy.command.AddToDoCommand;
import clippy.command.AddDeadlineCommand;
import clippy.command.AddEventCommand;

import clippy.exception.EmptyDescriptionException;
import clippy.exception.InvalidCommandException;
import clippy.exception.EmptyDateTimeException;

import clippy.task.TaskType;

import java.util.Arrays;

/**
 * Represents a parser that parses user-input into commands that can be executed by Clippy.
 */
public class Parser {
    private static final int TO_DO_MIN_LENGTH = 6;
    private static final int DEADLINE_MIN_LENGTH = 10;
    private static final int EVENT_MIN_LENGTH = 7;
    private static final int BY_GAP = 4;
    private static final int AT_GAP = 4;
    
    /**
     * Returns a command object that can be executed by Clippy.
     * 
     * @param input User-input.
     * @return A command object representing user-input.
     * @throws EmptyDescriptionException If user did not specify the description of a task.
     * @throws InvalidCommandException If user entered a invalid command.
     * @throws EmptyDateTimeException If user did not specify the date/time of a task.
     */
    public static Command parse(String input) throws EmptyDescriptionException,
            InvalidCommandException, EmptyDateTimeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else if (input.startsWith("delete")) {
            int indexOfTaskToDelete = Integer.parseInt(input.substring(7));
            return new DeleteCommand(indexOfTaskToDelete);
        } else if (input.startsWith("done")) {
            int indexOfDoneTask = Integer.parseInt(input.substring(5));
            return new DoneCommand(indexOfDoneTask);
        } else if (input.startsWith("find")) {
            return new FindCommand(input.substring(5));
        } else if (input.startsWith("update")) {
            String[] subInputs = input.split(" ");
            int length = subInputs.length;
            int indexOfTaskToBeUpdated = Integer.parseInt(subInputs[1]);
            if (containsBackSlash(subInputs)) {
                // update description of Deadline/Event OR update description of Deadline/Event + time
                int indexOfBackSlash = getIndexOfBackSlash(subInputs);
                String[] timeInArray = Arrays.copyOfRange(subInputs, indexOfBackSlash + 1, length);
                String time = String.join(" ", timeInArray);
                if (indexOfBackSlash == 2) {
                    // update time only
                    return new UpdateTimeCommand(indexOfTaskToBeUpdated, time);
                } else {
                    // update description and time
                    String[] descriptionInArray = Arrays.copyOfRange(subInputs, 2, indexOfBackSlash);
                    String description = String.join(" ", descriptionInArray);
                    return new UpdateDescriptionAndTimeCommand(indexOfTaskToBeUpdated, description, time);
                }
            } else {
                // update description of To-Do/Deadline/Event only
                String[] descriptionInArray = Arrays.copyOfRange(subInputs, 2,  length);
                String description = String.join(" ", descriptionInArray);
                return new UpdateDescriptionCommand(indexOfTaskToBeUpdated, description);
            }
        } else if (input.startsWith("todo")) {
            if (input.length() < TO_DO_MIN_LENGTH) {
                throw new EmptyDescriptionException(TaskType.TODO);
            } else {
                return new AddToDoCommand(input.substring(TO_DO_MIN_LENGTH - 1));
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() < DEADLINE_MIN_LENGTH) {
                throw new EmptyDescriptionException(TaskType.DEADLINE);
            }
            int indexOfSeparator = input.indexOf("/");
            String taskDescription = input.substring(DEADLINE_MIN_LENGTH - 1, indexOfSeparator - 1);
            String by = input.substring(indexOfSeparator + BY_GAP);
            if (by.isBlank()) {
                throw new EmptyDateTimeException();
            }
            return new AddDeadlineCommand(taskDescription, by);
        } else if (input.startsWith("event")) {
            if (input.length() < EVENT_MIN_LENGTH) {
                throw new EmptyDescriptionException(TaskType.EVENT);
            }
            int indexOfSeparator = input.indexOf("/");
            String taskDescription = input.substring(EVENT_MIN_LENGTH - 1, indexOfSeparator - 1);
            String at = input.substring(indexOfSeparator + AT_GAP);
            if (at.isBlank()) {
                throw new EmptyDateTimeException();
            }
            return new AddEventCommand(taskDescription, at);
        } else {
            throw new InvalidCommandException();
        }
    }
    
    private static int getIndexOfBackSlash(String[] subInputs) {
        int length = subInputs.length;
        for (int i = 0; i < length; i++) {
            String subInput = subInputs[i];
            if (subInput.charAt(0) == '/') {
                return i;
            }
        }
        return -1;
    }

    private static boolean containsBackSlash(String[] subInputs) {
        int length = subInputs.length;
        for (int i = 0; i < length; i++) {
            String subInput = subInputs[i];
            if (subInput.charAt(0) == '/') {
                return true;
            }
        }
        return false;
    }
}
