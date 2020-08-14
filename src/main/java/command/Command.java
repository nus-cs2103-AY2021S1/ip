package command;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an executable command
 */
public abstract class Command {
    protected String userInput;

    public Command(String userInput) {
        this.userInput = userInput;
    }

    public boolean isExit() {
        return this.userInput.equals("bye");
    }

    public abstract String sendRequest();

    public List<String> getContent() {
        return new ArrayList<>();
    }
}
