package duke.command;

/**
 * A subclass of Command which sends request to add duke.task to list.
 */
public class AddCommand extends Command {
    @Override
    public String sendRequest() {
        return "create";
    }
}
