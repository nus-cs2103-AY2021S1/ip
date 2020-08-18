package parser;

import commands.Command;

import java.util.ArrayList;
import java.util.function.Function;

public class Parser {
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

    public Parser() {
        allCommandInstances = new ArrayList<>();
    }

    public void registerCommand(Function<String, Command> constructor, String commandWord) {
        allCommandInstances.add(new CommandInstance(constructor, commandWord));
    }

    public Command parse(String raw) throws Exception {
        String[] tokens = raw.split(" ");
        if (tokens.length <= 0) {
            throw new Exception("Command not valid");
        }
        String word = tokens[0];
        for (CommandInstance instance: allCommandInstances) {
            if (word.equals(instance.commandWord)) {
                return instance.constructor.apply(raw);
            }
        }
        throw new Exception("Command not found");
    }
}
