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
    
    private static UpdateCommand parseUpdate(String input) {
        String[] subInputs = input.split(" ");
        
        int length = subInputs.length;
        int indexOfTaskToBeUpdated = Integer.parseInt(subInputs[1]);
        
        if (hasBackSlash(subInputs)) {
            // update description of Deadline/Event OR update description of Deadline/Event + time
            int indexOfBackSlash = getIndexOfBackSlash(subInputs);
            assert indexOfBackSlash >= 0 : "Invalid index of backslash";
            
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
            String[] descriptionInArray = Arrays.copyOfRange(subInputs, 2, length);
            String description = String.join(" ", descriptionInArray);
            
            return new UpdateDescriptionCommand(indexOfTaskToBeUpdated, description);
        }
    }

    private static AddToDoCommand parseToDo(String input) throws EmptyDescriptionException {
        if (input.length() < MIN_LENGTH_TO_DO) {
            throw new EmptyDescriptionException(TaskType.TODO);
        }
        
        return new AddToDoCommand(input.substring(MIN_LENGTH_TO_DO - 1));
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

    private static boolean hasBackSlash(String[] subInputs) {
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
