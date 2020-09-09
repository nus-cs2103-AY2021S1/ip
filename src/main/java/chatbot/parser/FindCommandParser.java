package chatbot.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

import chatbot.commands.Command;
import chatbot.commands.FindCommand;
import chatbot.common.CommandType;
import chatbot.data.Task;
import chatbot.exception.ChatbotException;
import chatbot.exception.InvalidDateFormatException;
import chatbot.exception.ParseException;

public class FindCommandParser implements Parser {

    private final CommandType type;

    public FindCommandParser(CommandType type) {
        this.type = type;
    }

    @Override
    public Command parse(String args) throws ChatbotException {
        switch (type) {
        case DATE:
            try {
                LocalDate date = LocalDate.parse(args);
                Predicate<Task> pred = task -> (
                        task.getDate() != null && task.getDate().equals(date));

                return new FindCommand(pred);
            } catch (DateTimeParseException e) {
                throw new InvalidDateFormatException();
            }
        case FIND:
            Predicate<Task> pred = task -> (task.getDescription().contains(args));
            return new FindCommand(pred);
        default:
            break;
        }

        throw new ParseException();
    }
}
