/**
 * Help Command Class to list Commands, their usage and their description.
 * Allows for listing of all Commands, as well as for a detailed description of one Command.
 */
public class Help extends Command {

    /** Query of User stored for reference */
    String[] query;

    Help(String[] query) throws WrongUsageException {
        this.name = "help";
        this.usage = "\nhelp [CommandName] (optional)";
        this.description = "Provides the list of all commands, " +
                "or a detailed description of a single command";
        if (query.length > 2) {
            throw new WrongUsageException(this.name, this.usage);
        }
        this.query = query;
    }

    /**
     * Returns String Response to User.
     *
     * @return String Response to User.
     * @throws UnknownCommandException If User types in an Unknown Command.
     */
    public String respond() throws UnknownCommandException {
        if (query.length == 1) {
            return listCommands();
        } else {
            return singleCommandHelp(query[1]);
        }
    }

    private String listCommands() {
        return "OH MY GOD MORTY THIS AGAIN?? REALLY? I expected this from Jerry but YOU? " +
                "I guess the apple really doesn't fall from the tree.\n\n" +
                "COMMAND: USAGE\n" + DataStorageInterface.listCommands();
    }

    private String singleCommandHelp(String query) throws UnknownCommandException {
        return DataStorageInterface.getUsage(query);
    }
}
