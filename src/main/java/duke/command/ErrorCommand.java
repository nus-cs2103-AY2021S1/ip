package duke.command;

import java.util.ArrayList;
import java.util.List;

/**
 * A subclass of Command which indicates an error in the input
 */
public class ErrorCommand extends Command {
    private final String errorMessage;

    public ErrorCommand(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    @Override
    public String sendRequest() {
        return "error";
    }

    @Override
    public List<String> getContent() {
        List<String> err = new ArrayList<>();
        err.add(errorMessage);
        return err;
    }
}

