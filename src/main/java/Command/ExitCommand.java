package main.java.Command;

import main.java.Storage.Storage;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.messageTemplate(ui.formatMessage("Bye! Hope to see you soon"));

        super.setExit(true);
    }
}
