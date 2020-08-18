public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    // Method to check whether the list command is valid
    protected void checkCommandValidity() throws InvalidCommandException{
        try {
            if (this.command.compareTo("list") != 0) {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException();
        }
    }


}
