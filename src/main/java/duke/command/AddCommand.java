package duke.command;

/** A subclass of Command which sends request to add task to list */
public class AddCommand extends Command {
    protected static final String ADD_REQUEST = "create";

    @Override
    public String sendRequest() {
        return ADD_REQUEST;
    }
}
