package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {

    public HelpCommand(String command) {
        super(command);
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        assert storage != null : "Storage object cannot be null";
        assert tasks != null : "TaskList object cannot be null";
        assert ui != null : "Ui object cannot be null";
        return ui.printHelp();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HelpCommand;
    }
}
