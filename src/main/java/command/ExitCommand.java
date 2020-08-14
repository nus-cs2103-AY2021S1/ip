package command;

import java.util.List;

public class ExitCommand extends Command {
    public ExitCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String sendRequest() {
        return "end";
    }
}
