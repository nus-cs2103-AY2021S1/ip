package duke.command;

import duke.storage.Storage;
import duke.util.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    protected static final String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";

    public static ExitCommand create() {
        return new ExitCommand();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.outputMessage(getClosingText());
    }

    private String getClosingText() {
        return CLOSING_MESSAGE;
    }

    @Override
    public boolean isExit() {
        return true;
    }


}
