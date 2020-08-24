package main.java.Command;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

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
