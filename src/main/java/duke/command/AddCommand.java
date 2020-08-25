package duke.command;

public abstract class AddCommand extends Command {

    protected final String description;

    public AddCommand(String description) {
        this.description = description;
    }
    
}