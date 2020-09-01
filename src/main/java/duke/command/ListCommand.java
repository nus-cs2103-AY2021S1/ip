package duke.command;

/** A subclass of Command which sends request to show list */
public class ListCommand extends Command {
    protected static final String LIST_REQUEST = "retrieval";

    @Override
    public String sendRequest() {
        return LIST_REQUEST;
    }
}
