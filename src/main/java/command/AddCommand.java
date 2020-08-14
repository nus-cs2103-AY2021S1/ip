package command;

import java.util.ArrayList;
import java.util.List;

public class AddCommand extends Command {
    public AddCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String sendRequest() {
        return "create";
    }

    @Override
    public List<String> getContent() {
        List<String> content = new ArrayList<>();
        content.add(this.userInput);
        return content;
    }
}
