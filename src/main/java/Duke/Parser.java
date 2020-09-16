package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.IncorrectCommand;
import command.ListCommand;
import command.RepeatCommand;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Parser is the class which makes sense of the user's commands.
 *
 * @author Joshua
 */
public class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TERMINATE = "bye";
    private static final String COMMAND_COMPLETE_TASK = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_DELETE_EVENT = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_REPEAT = "repeat";
    private static final String DATE_DEADLINE = "/by";
    private static final String DATE_EVENT = "/at";


    private static final DateTimeFormatter SAVE_READ_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates a Parser object.
     */
    public Parser() {
    }

    /**
     * Parses the input from the user and returns a command with respect to the
     * input from the user.
     *
     * @param fullCommand the given input from the user.
     * @return the command that will be carried out by Duke.
     */
    public static Command parse(String fullCommand) {
        String shortCommand;
        String restOfCommand;
        if (fullCommand.contains(" ")) {
            shortCommand = fullCommand.substring(0, fullCommand.indexOf(' '));
            restOfCommand = fullCommand.substring(shortCommand.length() + 1);
        } else {
            shortCommand = fullCommand;
            restOfCommand = "";
        }
        switch (shortCommand) {
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_TERMINATE:
            if (restOfCommand.isEmpty()) {
                return new ExitCommand();
            } else {
                return new IncorrectCommand("OOPS !!! Lo siento, pero no se que significa eso :-(");
            }
        case COMMAND_COMPLETE_TASK:
            return handleDone(restOfCommand);
        case COMMAND_ADD_DEADLINE:
            return handleDeadline(restOfCommand);
        case COMMAND_ADD_TODO:
            return new AddCommand(new Todo(restOfCommand));
        case COMMAND_ADD_EVENT:
            return handleEvent(restOfCommand);
        case COMMAND_DELETE_EVENT:
            return handleDelete(restOfCommand);
        case COMMAND_FIND:
            return new FindCommand(restOfCommand);
        case COMMAND_REPEAT:
            return handleRepeat(restOfCommand);
        default:
            return new IncorrectCommand("OOPS !!! Lo siento, pero no se que significa eso :-(");
        }
    }

    /**
     * Creates a Done command that will edit a task to be completed.
     *
     * @param restOfCommand the number of the task to be completed.
     * @return Done command that will complete the task.
     */
    private static Command handleDone(String restOfCommand) {
        if (restOfCommand.isEmpty()) {
            return new IncorrectCommand("OOPS !!! La descripcion de una tarea no puede estar vacia.");
        }
        int positionDone;
        try {
            positionDone = Integer.parseInt(restOfCommand);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("OOPS !!! Incapaz de completar");
        }
        positionDone = positionDone - 1;
        return new DoneCommand(positionDone);
    }

    /**
     * Creates a Delete command that will remove the task from the task list.
     *
     * @param restOfCommand the number of the task to be deleted
     * @return Delete command that will remove the task from the task list.
     */
    private static Command handleDelete(String restOfCommand) {
        if (restOfCommand.isEmpty()) {
            return new IncorrectCommand("OOPS !!! La descripcion de una tarea no puede estar vacia.");
        }
        int positionDone;
        try {
            positionDone = Integer.parseInt(restOfCommand);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("OOPS !!! Incapaz de completar");
        }
        positionDone = positionDone - 1;
        return new DeleteCommand(positionDone);
    }

    /**
     * Creates a Add Command and creates Deadline task to be added into the task list.
     *
     * @param restOfCommand the description and time to be used in the creation of the Deadline task.
     * @return Add command that will input the created Deadline into the task list.
     */
    private static Command handleDeadline(String restOfCommand) {
        if (restOfCommand.isEmpty()) {
            return new IncorrectCommand("OOPS !!! La descripcion de una tarea no puede estar vacia.");
        }
        if (!restOfCommand.contains(DATE_DEADLINE)) {
            return new IncorrectCommand("OOPS !!! Debe establecer una fecha limite para esta tarea.");
        }
        assert restOfCommand.contains(DATE_DEADLINE);
        int byPosition = restOfCommand.indexOf(DATE_DEADLINE);
        String taskDescription = restOfCommand.substring(0, byPosition);
        String dateDescription = restOfCommand.substring(byPosition + 4);
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(dateDescription, SAVE_READ_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(
                    "OOPS !!! Formato de fecha y hora incorrecto. Formatee como dd/MM/yyyy HHmm");
        }
        Deadline newDeadline = new Deadline(taskDescription);
        newDeadline.setTime(date);
        return new AddCommand(newDeadline);
    }

    /**
     * Creates a Add Command and creates Event task to be added into the task list.
     *
     * @param restOfCommand the description and time to be used in the creation of the Event task.
     * @return Add command that will input the created Event into the task list.
     */
    private static Command handleEvent(String restOfCommand) {
        if (restOfCommand.isEmpty()) {
            return new IncorrectCommand("OOPS !!! La descripción de una tarea no puede estar vacia.");
        }
        if (!restOfCommand.contains(DATE_EVENT)) {
            return new IncorrectCommand("OOPS !!! Debe establecer la hora del evento para esta tarea.");
        }
        assert restOfCommand.contains(DATE_EVENT);
        int atPosition = restOfCommand.indexOf(DATE_EVENT);
        String taskDescription = restOfCommand.substring(0, atPosition);
        String dateDescription = restOfCommand.substring(atPosition + 4);
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(dateDescription, SAVE_READ_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(
                    "OOPS !!! Formato de fecha y hora incorrecto. Formatee como dd/MM/yyyy HHmm");
        }
        Event newEvent = new Event(taskDescription);
        newEvent.setTime(date);
        return new AddCommand(newEvent);
    }

    public enum FrequencyOfRecurrence {
        daily,
        weekly,
        monthly
    }

    /**
     * Creates a RepeatCommand and sets the task in that position to be recurring.
     *
     * @param restOfCommand the position of the task to act on and the frequency of recurrence.
     * @return the RepeatCommand that will carry out the changing of the task.
     */
    private static Command handleRepeat(String restOfCommand) {
        int positionRepeat;
        if (restOfCommand.isEmpty()) {
            return new IncorrectCommand("OOPS !!! La descripción de una tarea no puede estar vacia.");
        }
        String[] splitWords = restOfCommand.split(" ");
        if (splitWords.length < 2) {
            return new IncorrectCommand("OOPS !!! No entiendo lo que estas diciendo, ¡intentalo de nuevo!");
        }
        try {
            positionRepeat = Integer.parseInt(splitWords[0]);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("OOPS !!! No entiendo lo que estas diciendo, ¡intentalo de nuevo!");
        }
        for (FrequencyOfRecurrence frequency : FrequencyOfRecurrence.values()) {
            if (splitWords[1].equals(frequency.toString())) {
                return new RepeatCommand(positionRepeat - 1, frequency);
            }
        }
        assert !restOfCommand.contains(FrequencyOfRecurrence.daily.toString());
        assert !restOfCommand.contains(FrequencyOfRecurrence.weekly.toString());
        assert !restOfCommand.contains(FrequencyOfRecurrence.monthly.toString());
        return new IncorrectCommand("OOPS !!! No entiendo lo que estas diciendo, ¡intentalo de nuevo!");
    }
}
