package duke;

import duke.command.Command;
import duke.exception.DukeParseException;

public class Parser {

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
