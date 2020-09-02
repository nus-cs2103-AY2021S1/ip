package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class OwoCommand extends Command {
    public static final String COMMAND_WORD = "owo";
    private static final String MESSAGE_OWO_ACKNOWLEDGEMENT = "uwu";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(MESSAGE_OWO_ACKNOWLEDGEMENT);
    }

}
