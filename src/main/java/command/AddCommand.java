package command;

import java.util.ArrayList;
import java.util.List;

/**
 * A subclass of Command which sends request to add task to list.
 */
public class AddCommand extends Command {
    public AddCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String sendRequest() {
        return "create";
    }

    /**
     * Get the content from the command which contains a task.
     *
     * @return a list of String containing the content in the command.
     */
    @Override
    public List<String> getContent() {
        List<String> content = new ArrayList<>();
        content.add(this.userInput);
        return content;
    }
}
