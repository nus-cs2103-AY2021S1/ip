package parser;

import commands.Command;
import exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.function.Function;

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

    public CommandParser() {
        allCommandInstances = new ArrayList<>();
    }

    public void registerCommand(Function<String, Command> constructor, String commandWord) {
        allCommandInstances.add(new CommandInstance(constructor, commandWord));
    }

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
