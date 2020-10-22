package clippy.parser;

import clippy.command.AddDeadlineCommand;
import clippy.command.AddEventCommand;
import clippy.command.AddToDoCommand;
import clippy.command.Command;
import clippy.command.DeleteCommand;
import clippy.command.DoneCommand;
import clippy.command.ExitCommand;
import clippy.command.FindCommand;
import clippy.command.HelpCommand;
import clippy.command.ListCommand;
import clippy.command.UpdateCommand;
import clippy.command.UpdateDescriptionAndTimeCommand;
import clippy.command.UpdateDescriptionCommand;
import clippy.command.UpdateTimeCommand;

import clippy.exception.EmptyDateTimeException;
import clippy.exception.EmptyDescriptionException;
import clippy.exception.InvalidCommandException;

import clippy.task.TaskType;

import java.util.Arrays;

/**
 * Represents a parser that parses user-input into commands that can be executed by Clippy.
 */
public class Parser {
    private static final int MIN_LENGTH_TO_DO = 6;
    private static final int MIN_LENGTH_DEADLINE = 10;
    private static final int MIN_LENGTH_EVENT = 7;
    
    private static final int GAP_BY = 4;
    private static final int GAP_AT = 4;
    
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
            return parseBye();
        } else if (input.equals("list")) {
            return parseList();
        } else if (input.equals("help")) {
            return parseHelp();
        } else if (input.startsWith("delete")) {
            return parseDelete(input);
        } else if (input.startsWith("done")) {
            return parseDone(input);
        } else if (input.startsWith("find")) {
            return parseFind(input);
        } else if (input.startsWith("update")) {
            return parseUpdate(input);
        } else if (input.startsWith("todo")) {
            return parseToDo(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadline(input);
        } else if (input.startsWith("event")) {
            return parseEvent(input);
        } else {
            throw new InvalidCommandException();
        }
    }
    
    private static ExitCommand parseBye() {
        return new ExitCommand();
    }

    private static ListCommand parseList() {
        return new ListCommand();
    }

    private static HelpCommand parseHelp() {
        return new HelpCommand();
    }
    
    private static DeleteCommand parseDelete(String input) {
        int indexOfTaskToDelete = Integer.parseInt(input.substring(7));
        return new DeleteCommand(indexOfTaskToDelete);
    }

    private static DoneCommand parseDone(String input) {
        int indexOfDoneTask = Integer.parseInt(input.substring(5));
        return new DoneCommand(indexOfDoneTask);
    }

    private static FindCommand parseFind(String input) {
        return new FindCommand(input.substring(5));
    }
    
    private static UpdateCommand parseUpdate(String input) throws InvalidCommandException {
        int indexOfTask = getIndexOfTask(input);
        
        if (isUpdatingDescriptionOnly(input)) {
            return parseUpdateDescriptionOnly(input, indexOfTask);
        } else if (isUpdatingTimeOnly(input)) {
            return parseUpdateTimeOnly(input, indexOfTask);
        } else if (isUpdatingDescriptionAndTime(input)) {
            return parseUpdateDescriptionAndTime(input, indexOfTask);
        } else {
            throw new InvalidCommandException();
        }

    }
    
    private static int getIndexOfTask(String input) {
        String[] subInputs = input.split(" ");

        return Integer.parseInt(subInputs[1]);
    }

    private static int getIndexOfBackSlashFieldInArray(String[] subInputs) {
        int length = subInputs.length;
        for (int i = 0; i < length; i++) {
            String subInput = subInputs[i];
            if (subInput.charAt(0) == '/') {
                return i;
            }
        }
        return -1;
    }
    
    private static boolean isUpdatingDescriptionOnly(String input) {
        if (input.contains(" /by ") || input.contains(" /at ")) {
            return false;
        }
        return true;
    }
    
    private static boolean isUpdatingTimeOnly(String input) {
        String[] subInputs = input.split(" ");
        int indexOfBackSlashFieldInArray = getIndexOfBackSlashFieldInArray(subInputs);
        assert indexOfBackSlashFieldInArray >= 0 : "Invalid index of backslash";
        
        if (indexOfBackSlashFieldInArray == 2) {
            return true;
        }
        return false;
    }
    
    private static boolean isUpdatingDescriptionAndTime(String input) {
        String[] subInputs = input.split(" ");
        int indexOfBackSlashFieldInArray = getIndexOfBackSlashFieldInArray(subInputs);
        assert indexOfBackSlashFieldInArray >= 0 : "Invalid index of backslash";

        if (indexOfBackSlashFieldInArray > 2) {
            return true;
        }
        return false;
    }
    
    private static String getTimeOfUpdate(String input) throws InvalidCommandException {
        String[] subInputs;
        if (input.contains(" /at ")) {
            subInputs = input.split(" /at ");
        } else if (input.contains(" /by ")) {
            subInputs = input.split(" /by ");
        } else {
            throw new InvalidCommandException();
        }

        return subInputs[1];
    }
    
    private static String getDescriptionOfUpdate(String input) {
        String[] subInputs;
        if (input.contains(" /at ")) {
            subInputs = input.split(" /at ");
        } else if (input.contains(" /by ")) {
            subInputs = input.split(" /by ");
        } else {
            subInputs = input.split(" /by ");
        }
        
        String inputWithoutTime = subInputs[0];
        
        String[] inputWithoutTimeSubInputs = inputWithoutTime.split(" ");
        
        String[] descriptionInSplitArray = Arrays.copyOfRange(inputWithoutTimeSubInputs, 2, 
                inputWithoutTimeSubInputs.length);
        
        String description = String.join(" ", descriptionInSplitArray);
        
        return description;
    }
    
    private static UpdateTimeCommand parseUpdateTimeOnly(String input, int indexOfTask) throws 
            InvalidCommandException {
        String time = getTimeOfUpdate(input);
        return new UpdateTimeCommand(indexOfTask, time);
    }
    
    private static UpdateDescriptionAndTimeCommand parseUpdateDescriptionAndTime(String input, int indexOfTask) throws 
            InvalidCommandException {
        String description = getDescriptionOfUpdate(input);
        String time = getTimeOfUpdate(input);
        
        return new UpdateDescriptionAndTimeCommand(indexOfTask, description, time);
    }
    
    private static UpdateDescriptionCommand parseUpdateDescriptionOnly(String input, int indexOfTask) {
        String description = getDescriptionOfUpdate(input);

        return new UpdateDescriptionCommand(indexOfTask, description);
    }

    private static AddToDoCommand parseToDo(String input) throws EmptyDescriptionException {
        if (input.length() < MIN_LENGTH_TO_DO) {
            throw new EmptyDescriptionException(TaskType.TODO);
        }
        
        String toDoDescription = input.substring(MIN_LENGTH_TO_DO - 1);
        
        return new AddToDoCommand(toDoDescription);
    }

    private static AddDeadlineCommand parseDeadline(String input) throws EmptyDescriptionException, 
            EmptyDateTimeException {
        if (input.length() < MIN_LENGTH_DEADLINE) {
            throw new EmptyDescriptionException(TaskType.DEADLINE);
        }
        
        int indexOfSeparator = input.indexOf("/");
        
        String taskDescription = input.substring(MIN_LENGTH_DEADLINE - 1, indexOfSeparator - 1);
        String by = input.substring(indexOfSeparator + GAP_BY);
        
        if (by.isBlank()) {
            throw new EmptyDateTimeException();
        }
        
        return new AddDeadlineCommand(taskDescription, by);
    }

    private static AddEventCommand parseEvent(String input) throws EmptyDescriptionException, EmptyDateTimeException {
        if (input.length() < MIN_LENGTH_EVENT) {
            throw new EmptyDescriptionException(TaskType.EVENT);
        }
        
        int indexOfSeparator = input.indexOf("/");
        
        String taskDescription = input.substring(MIN_LENGTH_EVENT - 1, indexOfSeparator - 1);
        String at = input.substring(indexOfSeparator + GAP_AT);
        
        if (at.isBlank()) {
            throw new EmptyDateTimeException();
        }
        
        return new AddEventCommand(taskDescription, at);
    }
}
