public class Parser {

    /**
     * Parse user input and returns an enum of the command.
     * @param fullCommand is the user input from the terminal.
     * @return an enum representing the command from the user.
     */
    public static Commands parse(String fullCommand) {
        String[] splitString = fullCommand.split(" ");
        Commands command;
        try {
            command = Commands.valueOf(splitString[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            command = Commands.UNKNOWN;
        }
        return command;
    }
}
