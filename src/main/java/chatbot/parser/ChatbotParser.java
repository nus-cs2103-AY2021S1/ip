package chatbot.parser;

import chatbot.commands.Command;
import chatbot.commands.ExitCommand;
import chatbot.commands.ShowAllCommand;
import chatbot.common.CommandType;
import chatbot.common.Message;
import chatbot.common.TaskType;
import chatbot.exception.ChatbotException;

/**
 * A class that parses the command input given by the user.
 */

public class ChatbotParser {

    /**
     * Parses the user input and returns an executable command.
     * @param fullCmd the user input
     * @return command matching the user input
     * @throws ChatbotException if command is invalid
     */
    public static Command parseCommand(String fullCmd) throws ChatbotException {

        CommandType type;

        // extract type of command
        String text = fullCmd.trim();
        String typeStr = text.split(" ")[0].trim();

        // extract arguments from command
        int index = typeStr.length();
        String arguments = text.substring(index).trim();

        try {
            type = CommandType.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ChatbotException(Message.MESSAGE_UNKNOWN_COMMAND);
        }

        switch (type) {
        case BYE:
            return new ExitCommand();
        case DATE:
            return new FindCommandParser(CommandType.DATE).parse(arguments);
        case DELETE:
            return new DeleteCommandParser().parse(arguments);
        case DEADLINE:
            return new AddCommandParser(TaskType.DEADLINE).parse(arguments);
        case DONE:
            return new DoneCommandParser().parse(arguments);
        case EVENT:
            return new AddCommandParser(TaskType.EVENT).parse(arguments);
        case FIND:
            return new FindCommandParser(CommandType.FIND).parse(arguments);
        case LIST:
            return new ShowAllCommand();
        case SORT:
            return new SortCommandParser().parse(arguments);
        case TODO:
            return new AddCommandParser(TaskType.TODO).parse(arguments);
        default:
            throw new ChatbotException(Message.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
