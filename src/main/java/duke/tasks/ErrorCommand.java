package duke.tasks;

import java.io.IOException;

public class ErrorCommand extends Command {
    protected String errorMsg;

    public ErrorCommand() {
        this.errorMsg = "Sorry the input is of a wrong format";
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        return this.errorMsg;
    }
}
