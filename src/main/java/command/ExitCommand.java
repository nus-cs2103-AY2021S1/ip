package main.java.command;

import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.getMessageTemplate(ui.formatMessage("Bye! Hope to see you soon"));

        super.setExit(true);
    }
}
