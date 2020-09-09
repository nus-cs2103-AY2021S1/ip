package chatbot.parser;

import chatbot.commands.Command;
import chatbot.commands.SortCommand;

import chatbot.common.Message;

import chatbot.exception.ChatbotException;
import chatbot.util.comparator.TaskByDateComparator;

public class SortCommandParser implements Parser {
    @Override
    public Command parse(String args) throws ChatbotException {
        String[] arr = args.split("/by");

        if (arr.length == 0 || arr.length == 1) {
            throw new ChatbotException(Message.MESSAGE_UNKNOWN_COMMAND);
        }

        String descriptor = arr[1].trim();
        TaskByDateComparator comp = new TaskByDateComparator();

        if (descriptor.equals("earliest")) {
            return new SortCommand(comp);
        } else if (descriptor.equals("latest")) {
            return new SortCommand(comp.reversed());
        } else {
            throw new ChatbotException(Message.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
