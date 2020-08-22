package parser;

import commands.Command;
import exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * A parser that parses raw commands to real commands.
 */
public class CommandParser {
    ///helper class
    private static class CommandInstance {
        private Function<String, Command> constructor;
        private String commandWord;

        public CommandInstance(Function<String, Command> constructor, String commandWord) {
            this.constructor = constructor;
            this.commandWord = commandWord;
        }
    }
    ArrayList<CommandInstance> allCommandInstances;

    /**
     * Constructor
     */
    public CommandParser() {
        allCommandInstances = new ArrayList<>();
    }

    /**
     * Registers a command to the parser, receive constructor provider and the command word.
     * It makes creating a new command easier, less error prone.
     * @param constructor: constructor provider
     * @param commandWord: command words: add, delete, done, ...
     */
    public void registerCommand(Function<String, Command> constructor, String commandWord) {
        allCommandInstances.add(new CommandInstance(constructor, commandWord));
    }

    /**
     * Explicitly parse the command from raw strings to Command objeccts
     * @param raw: raw command as a strinjg
     * @return a new Command object
     * @throws InvalidCommandException when systax fail. It is helpful to return errors to users;
     */
    public Command parse(String raw) throws InvalidCommandException {
        String[] tokens = raw.split(" ");
        if (tokens.length <= 0) {
            throw new InvalidCommandException("Command not valid");
        }
        String word = tokens[0];
        for (CommandInstance instance: allCommandInstances) {
            if (word.equals(instance.commandWord)) {
                return instance.constructor.apply(raw);
            }
        }
        throw new InvalidCommandException("Command not found");
    }
}
