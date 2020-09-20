package duke.tasks;

import java.util.Arrays;
import java.util.Optional;

import duke.command.CommandHelp;

/**
 * TextParser to handle parsing of commands and possible cleaning.
 */
public class TextParser {
    public TextParser(){
    }
    /**
     * Parse String Input into the Command Parser to return a Enum of the command encoded.
     *
     * @param cmd the string command for the Duke Application
     * @return Command Enumeration
     */
    public CommandHelp parseHelpCommand(String cmd) {
        String cleaned = cmd.toLowerCase();
        Optional<CommandHelp> given = Arrays.stream(CommandHelp.values())
                .filter(commandHelp -> commandHelp.getCode().equals(cleaned))
                .findFirst();
        given = given.isEmpty() ? Optional.of(CommandHelp.ERROR) : given;
        return given.get();
    }
    /**
     * inputs string, processes and cleans the text for the chatbot
     * via adding a ending token seperator
     * @param userInput Direct user input of the string
     * @return Cleaned user input
     */
    public String cleanInput(String userInput) {
        return userInput.strip();
    }

}
