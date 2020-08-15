package command;

/**
 * A subclass of Command which sends request to add task to list.
 */
public class AddCommand extends Command {
    public AddCommand(String content) {
        this.content = content;
    }

    @Override
    public String sendRequest() {
        return "create";
    }
}
