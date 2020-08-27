package duke;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.IncorrectCommand;
import command.ListCommand;
import command.FindCommand;

import task.Deadline;
import task.Event;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser is the class which makes sense of the user's commands.
 *
 * @author Joshua
 */
public class Parser {
    private final static String COMMAND_LIST = "list";
    private final static String COMMAND_TERMINATE = "bye";
    private final static String COMMAND_COMPLETE_TASK = "done";
    private final static String COMMAND_ADD_TODO = "todo";
    private final static String COMMAND_ADD_DEADLINE = "deadline";
    private final static String COMMAND_ADD_EVENT = "event";
    private final static String COMMAND_DELETE_EVENT = "delete";
    private final static String COMMAND_FIND = "find";
    private final static String DATE_DEADLINE = "/by";
    private final static String DATE_EVENT = "/at";

    private final static DateTimeFormatter SAVE_READ_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates a Parser object.
     */
    public Parser() {
    }

    /**
     * Types of task that could result from the parsed user command.
     */
    enum TypeOfCommand {
        DONE,
        DEADLINE,
        TODO,
        EVENT,
        DELETE,
        FIND
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
                return new IncorrectCommand("OOPS !!! Lo siento, pero no sé qué significa eso :-(");
            }
        case COMMAND_COMPLETE_TASK:
            return handle(restOfCommand, TypeOfCommand.DONE);
        case COMMAND_ADD_DEADLINE:
            return handle(restOfCommand, TypeOfCommand.DEADLINE);
        case COMMAND_ADD_TODO:
            return handle(restOfCommand, TypeOfCommand.TODO);
        case COMMAND_ADD_EVENT:
            return handle(restOfCommand, TypeOfCommand.EVENT);
        case COMMAND_DELETE_EVENT:
            return handle(restOfCommand, TypeOfCommand.DELETE);
        case COMMAND_FIND:
            return handle(restOfCommand, TypeOfCommand.FIND);
        default:
            return new IncorrectCommand("OOPS !!! Lo siento, pero no sé qué significa eso :-(");
        }
    }

    /**
     * Handles the creation of more complicated commands that require interaction with the TaskList.
     *
     * @param restOfCommand the content of the task to be added after initial command.
     * @param TypeOfCommand the type of command to be carried out.
     * @return the command that will finally be carried out by Duke.
     */
    private static Command handle(String restOfCommand, TypeOfCommand TypeOfCommand) {
        if (restOfCommand.isEmpty()) {
            return new IncorrectCommand("☹ OOPS !!! La descripción de una tarea no puede estar vacía.");
        }
        switch (TypeOfCommand) {
        case DONE:
            int positionDone;
            try {
                positionDone = Integer.parseInt(restOfCommand);
            } catch (NumberFormatException e) {
                return new IncorrectCommand("☹ OOPS !!! Incapaz de completar");
            }
            positionDone = positionDone - 1;
            return new DoneCommand(positionDone);
        case DELETE:
            try {
                positionDone = Integer.parseInt(restOfCommand);
            } catch (NumberFormatException e) {
                return new IncorrectCommand("☹ OOPS !!! Incapaz de completar");
            }
            positionDone = positionDone - 1;
            return new DeleteCommand(positionDone);
        case TODO:
           return new AddCommand(new Todo(restOfCommand));
        case DEADLINE:
            if (!restOfCommand.contains(DATE_DEADLINE)) {
                return new IncorrectCommand("☹ OOPS !!! Debe establecer una fecha límite para esta tarea.");
            }
            int byPosition = restOfCommand.indexOf(DATE_DEADLINE);
            String taskDescription = restOfCommand.substring(0, byPosition);
            String dateDescription = restOfCommand.substring(byPosition + 4);
            LocalDateTime date;
            try {
               date = LocalDateTime.parse(dateDescription, SAVE_READ_DATETIME_FORMAT);
            } catch (DateTimeParseException e) {
                return new IncorrectCommand("☹ OOPS !!! Formato de fecha y hora incorrecto. Formatee como dd/MM/yyyy HHmm");
            }
            Deadline newDeadline = new Deadline(taskDescription);
            newDeadline.setTime(date);
            return new AddCommand(newDeadline);
        case EVENT:
            if (!restOfCommand.contains(DATE_EVENT)) {
                return new IncorrectCommand("☹ OOPS !!! Debe establecer la hora del evento para esta tarea.");
            }
            int atPosition = restOfCommand.indexOf(DATE_EVENT);
            taskDescription = restOfCommand.substring(0, atPosition);
            dateDescription = restOfCommand.substring(atPosition + 4);
            try {
                date = LocalDateTime.parse(dateDescription, SAVE_READ_DATETIME_FORMAT);
            } catch (DateTimeParseException e) {
                return new IncorrectCommand("☹ OOPS !!! Formato de fecha y hora incorrecto. Formatee como dd/MM/yyyy HHmm");
            }
            Event newEvent = new Event(taskDescription);
            newEvent.setTime(date);
            return new AddCommand(newEvent);
        case FIND:
            return new FindCommand(restOfCommand);
        }
        return null;
    }
}
