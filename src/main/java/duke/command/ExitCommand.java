package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "bb cya again!";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
