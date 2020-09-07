package chatbot.parser;

import chatbot.commands.*;

import chatbot.common.Message;
import chatbot.common.Type;

import chatbot.data.Deadline;
import chatbot.data.Event;
import chatbot.data.Task;
import chatbot.data.Todo;

import chatbot.exception.ChatbotException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.function.Predicate;

/**
 * A class that parses the command input given by the user.
 */

public class Parser {

    /**
     * Parses the user input and returns an executable command.
     * @param fullCmd the user input
     * @return command matching the user input
     * @throws ChatbotException if command is invalid
     */
    public static Command parse(String fullCmd) throws ChatbotException {

        Type type;
        Command invalidCommand = new InvalidCommand();

        // extract type of command
        String text = fullCmd.trim();
        String typeStr = text.split(" ")[0].trim();

        // extract arguments from command
        int index = typeStr.length();
        String arguments = text.substring(index).trim();

        try {
            type = Type.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return invalidCommand;
        }

        switch (type) {
        case BYE:
            return new ExitCommand();
        case DATE:
            return parseFindByDateCommand(arguments);
        case DELETE:
            return parseDeleteCommand(arguments);
        case DEADLINE:
            return parseAddDeadlineCommand(arguments);
        case DONE:
            return parseDoneCommand(arguments);
        case EVENT:
            return parseAddEventCommand(arguments);
        case FIND:
            return parseFindByKeywordCommand(arguments);
        case LIST:
            return new ShowAllCommand();
        case TODO:
            return parseAddTodoCommand(arguments);
        default:
            return invalidCommand;
        }
    }

    private static Command parseDeleteCommand(String args) throws ChatbotException {
        try {
            int index = Integer.parseInt(args) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new ChatbotException(Message.INVALID_NUMBER);
        }
    }

    private static Command parseDoneCommand(String args) throws ChatbotException {
        try {
            int index = Integer.parseInt(args) - 1;
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new ChatbotException(Message.INVALID_NUMBER);
        }
    }

    private static Command parseAddTodoCommand(String args) throws ChatbotException {
        if (args.length() == 0) {
            throw new ChatbotException(Message.TASK_EMPTY);
        }
        Todo task = Todo.newTodo(args);
        return new AddCommand(task);
    }

    private static Command parseAddDeadlineCommand(String args) throws ChatbotException {
        if (args.length() == 0) {
            throw new ChatbotException(Message.TASK_EMPTY);
        }

        try {
            String description = args.split("/by")[0].trim();
            String dateString = args.split("/by")[1].trim();
            LocalDate date = LocalDate.parse(dateString);
            
            Deadline dl = Deadline.newDeadline(description, date);
            
            return new AddCommand(dl);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatbotException(Message.DATE_MISSING);
        } catch (DateTimeParseException e) {
            throw new ChatbotException(Message.INVALID_DATE);
        }
    }

    private static Command parseAddEventCommand(String args) throws ChatbotException {
        if (args.length() == 0) {
            throw new ChatbotException(Message.TASK_EMPTY);
        }

        try {
            String description = args.split("/at")[0].trim();
            String dateString = args.split("/at")[1].trim();
            LocalDate date = LocalDate.parse(dateString);

            Event evt = Event.newEvent(description, date);

            return new AddCommand(evt);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatbotException(Message.DATE_MISSING);
        } catch (DateTimeParseException e) {
            throw new ChatbotException(Message.INVALID_DATE);
        }
    }

    private static Command parseFindByDateCommand(String args) throws ChatbotException {
        try {
            LocalDate date = LocalDate.parse(args);
            Predicate<Task> pred = task -> (task.getDate() != null && task.getDate().equals(date));
            return new FindCommand(pred);
        } catch (DateTimeParseException e) {
            throw new ChatbotException(Message.INVALID_DATE);
        }
    }

    private static Command parseFindByKeywordCommand(String args){
        Predicate<Task> pred = task -> (task.getDescription().contains(args));
        return new FindCommand(pred);
    }
}
