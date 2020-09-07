package chatbot.parser;

import chatbot.commands.*;
import chatbot.common.Message;
import chatbot.common.Type;
import chatbot.data.Deadline;
import chatbot.data.Event;
import chatbot.data.Todo;
import chatbot.exception.ChatbotException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A class that parses the command input given by the user.
 */

public class Parser {

    /**
     * Parses the user input and returns an executable command.
     * @param fullCmd the user input
     * @return command matching the user input
     * @throws ChatbotException
     */
    public static Command parse(String fullCmd) throws ChatbotException {

        Type type;

        // parse out the type of command
        String text = fullCmd.trim();
        String typeStr = text.split(" ")[0].trim();

        // parse out the arguments of the command
        int index = typeStr.length();
        String arguments = text.substring(index).trim();

        try {
            type = Type.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new InvalidCommand();
        }

        boolean isShowAll = type == Type.LIST;
        boolean isFindByDate = type == Type.DATE;
        boolean isAddTodo = type == Type.TODO;
        boolean isAddDeadline = type == Type.DEADLINE;
        boolean isAddEvent = type == Type.EVENT;
        boolean isAction = type == Type.DELETE || type == Type.DONE;
        boolean isFind = type == Type.FIND;
        boolean isExit = type == Type.BYE;

        if (isShowAll) {
            return new ShowAllCommand();
        } else if (isFindByDate) {
            return parseFindByDateCommand(arguments);
        } else if (isAddTodo) {
            return parseAddTodoCommand(arguments);
        } else if (isAddDeadline) {
            return parseAddDeadlineCommand(arguments);
        } else if (isAddEvent) {
            return parseAddEventCommand(arguments);
        } else if (isAction) {
            return new ActionCommand(type, arguments);
        } else if (isExit) {
            return new ExitCommand();
        } else if (isFind) {
            return new FindCommand(arguments);
        } else {
            return new InvalidCommand();
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
            return new FindByDateCommand(date);
        } catch (DateTimeParseException e) {
            throw new ChatbotException(Message.INVALID_DATE);
        }
    }

}
