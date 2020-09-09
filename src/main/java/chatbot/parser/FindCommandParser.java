package chatbot.parser;

import chatbot.commands.Command;
import chatbot.commands.FindCommand;

import chatbot.common.CommandType;
import chatbot.common.Message;

import chatbot.data.Task;

import chatbot.exception.ChatbotException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.function.Predicate;

public class FindCommandParser implements Parser {

    CommandType type;

    public FindCommandParser(CommandType type) {
        this.type = type;
    }

    @Override
    public Command parse(String args) throws ChatbotException {
        switch (type) {
        case DATE:
            try {
                LocalDate date = LocalDate.parse(args);
                Predicate<Task> pred = task ->
                        (task.getDate() != null && task.getDate().equals(date));

                return new FindCommand(pred);
            } catch (DateTimeParseException e) {
                throw new ChatbotException(Message.MESSAGE_INVALID_DATE_FORMAT);
            }
        case FIND:
            Predicate<Task> pred = task -> (task.getDescription().contains(args));
            return new FindCommand(pred);
        }

        throw new ChatbotException(Message.MESSAGE_UNKNOWN_COMMAND);
    }
}
