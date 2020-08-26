package Duke;

import Command.*;

import Task.Deadline;
import Task.Event;
import Task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private final static String ACTION_LIST = "list";
    private final static String TERMINATION = "bye";
    private final static String TASK_COMPLETED = "done";
    private final static String TASK_TODO = "todo";
    private final static String TASK_DEADLINE = "deadline";
    private final static String TASK_EVENT = "event";
    private final static String DELETE_EVENT = "delete";
    private final static String DEADLINE_DATE = "/by";
    private final static String EVENT_DATE = "/at";
    private final static DateTimeFormatter SAVE_READ_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Parser() {
    }

    enum typeOfTask {
        DONE,
        DEADLINE,
        TODO,
        EVENT,
        DELETE
    }

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
            case ACTION_LIST:
                return new ListCommand();
            case TERMINATION:
                if (restOfCommand.isEmpty()) {
                    return new ExitCommand();
                } else {
                    return new IncorrectCommand("OOPS !!! Lo siento, pero no sé qué significa eso :-(");
                }
            case TASK_COMPLETED:
                return handle(restOfCommand, typeOfTask.DONE);
            case TASK_DEADLINE:
                return handle(restOfCommand, typeOfTask.DEADLINE);
            case TASK_TODO:
                return handle(restOfCommand, typeOfTask.TODO);
            case TASK_EVENT:
                return handle(restOfCommand, typeOfTask.EVENT);
            case DELETE_EVENT:
                return handle(restOfCommand, typeOfTask.DELETE);
            default:
                return new IncorrectCommand("OOPS !!! Lo siento, pero no sé qué significa eso :-(");
        }
    }

    private static Command handle(String restOfCommand, typeOfTask typeOfTask) {
        if (restOfCommand.isEmpty()) {
            return new IncorrectCommand("☹ OOPS !!! La descripción de una tarea no puede estar vacía.");
        }
        switch (typeOfTask) {
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
                if (!restOfCommand.contains(DEADLINE_DATE)) {
                    return new IncorrectCommand("☹ OOPS !!! Debe establecer una fecha límite para esta tarea.");
                }
                int byPosition = restOfCommand.indexOf(DEADLINE_DATE);
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
                if (!restOfCommand.contains(EVENT_DATE)) {
                    return new IncorrectCommand("☹ OOPS !!! Debe establecer la hora del evento para esta tarea.");
                }
                int atPosition = restOfCommand.indexOf(EVENT_DATE);
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
        }
        return null;
    }
}
