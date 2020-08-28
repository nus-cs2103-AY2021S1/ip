/**
 * Help Command Class to list Commands, their usage and their description.
 */
public class Help extends Command{

    /** Query of User stored for reference */
    String[] query;

    Help(String[] query) throws WrongUsageException{
        this.name = "help";
        this.usage = "help [CommandName](optional)";
        this.description = "Provides the list of all commands, " +
                "or a detailed description of a single command";
        if(query.length > 2){
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
    public String response() throws UnknownCommandException{
        if(query.length==1){
            return listCommands();
        } else{
            return singleCommandHelp(query[1]);
        }
    }


    private String listCommands(){
        return "COMMAND: USAGE\n" + DataStorageInterface.listCommands();
    }

    private String singleCommandHelp(String query) throws UnknownCommandException{
        return DataStorageInterface.getUsage(query);
    }
}
