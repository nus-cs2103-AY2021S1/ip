package chatbot.parser;

import chatbot.commands.AddCommand;
import chatbot.commands.Command;

import chatbot.common.Message;
import chatbot.common.TaskType;

import chatbot.data.Deadline;
import chatbot.data.Event;
import chatbot.data.Todo;

import chatbot.exception.ChatbotException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommandParser implements Parser {

    TaskType type;

    public AddCommandParser(TaskType type) {
        this.type = type;
    }

    @Override
    public Command parse(String args) throws ChatbotException {

        if (args.length() == 0) {
            throw new ChatbotException(Message.MESSAGE_EMPTY_TASK);
        }

        switch (type) {
        case TODO:
            Todo task = Todo.newTodo(args);
            return new AddCommand(task);
        case EVENT:
            try {
                String description = args.split("/at")[0].trim();
                String dateString = args.split("/at")[1].trim();
                LocalDate date = LocalDate.parse(dateString);

                Event e = Event.newEvent(description, date);

                return new AddCommand(e);

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ChatbotException(Message.MESSAGE_EMPTY_DATE);
            } catch (DateTimeParseException e) {
                throw new ChatbotException(Message.MESSAGE_INVALID_DATE_FORMAT);
            }
        case DEADLINE:
            try {
                String description = args.split("/by")[0].trim();
                String dateString = args.split("/by")[1].trim();
                LocalDate date = LocalDate.parse(dateString);

                Deadline dl = Deadline.newDeadline(description, date);

                return new AddCommand(dl);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ChatbotException(Message.MESSAGE_EMPTY_DATE);
            } catch (DateTimeParseException e) {
                throw new ChatbotException(Message.MESSAGE_INVALID_DATE_FORMAT);
            }
        default:
            break;
        }

        throw new ChatbotException(Message.MESSAGE_UNKNOWN_COMMAND);
    }
}
