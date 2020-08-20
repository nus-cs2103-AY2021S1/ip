package duke.command;

/** A subclass of Command which sends request to show list */
public class ListCommand extends Command {
    @Override
    public String sendRequest() {
        return "retrieval";
    }
}
