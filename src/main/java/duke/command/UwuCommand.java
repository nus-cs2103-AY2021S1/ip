package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class UwuCommand extends Command {
    public static final String COMMAND_WORD = "uwu";
    private static final String MESSAGE_UWU_ACKNOWLEDGEMENT = "owo";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(MESSAGE_UWU_ACKNOWLEDGEMENT);
    }

}
