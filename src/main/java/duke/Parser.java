package duke;

import duke.command.Command;
import duke.exception.DukeParseException;

/**
 * A parser to read the command lines detected and
 * determine which type of command it is refering to.
 */
public class Parser {

    /**
     * Parse the input command and returns the command object
     * if the command is valid.
     *
     * It will take the first part of the command line and search through
     * an enumeration of commands to check if such command exists, if it does,
     * it will create command object according to the command type and store the
     * original command in it.
     * If such command does not exists, DukeParseException will be thrown and caught, then
     * an 'invalid command' message will be send though the ui to alert the user.
     * @param inputCommand Detected command line of string type
     * @return The command detected with input command stored in it
     * @throws DukeParseException If invalid command
     */
    public static Command parse(String inputCommand) throws DukeParseException {

        if(inputCommand == null || inputCommand.length() == 0) {
            throw new DukeParseException("Empty command!");
        }

        String commandName = inputCommand.split(" ")[0];
        boolean isCommand = false;
        Command command = null;

        try{
            String className = "duke.command." + Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1) + "Command";
            for(DukeCommand comm: DukeCommand.values()) {
                if(commandName.equals(comm.getCommand())) {
                    command = (Command) Class.forName(className).getConstructor(String.class).newInstance(inputCommand);
                    isCommand = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DukeParseException("Unable to load command.");
        }


        if(isCommand) {
            return command;
        } else {
            throw new DukeParseException("Command '" + commandName + "' not found!");
        }
    }
}
