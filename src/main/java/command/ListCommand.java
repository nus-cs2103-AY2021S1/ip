package command;

/**
 * A subclass of Command which sends request to show list.
 */
public class ListCommand extends Command {
    public ListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String sendRequest() {
        return "retrieval";
    }
}
