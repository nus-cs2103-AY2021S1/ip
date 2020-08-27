package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class OwoCommand extends Command {
    public static final String COMMAND_WORD = "owo";
    public static final String MESSAGE_OWO_ACKNOWLEDGEMENT = "uwu";

    public OwoCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(MESSAGE_OWO_ACKNOWLEDGEMENT);
    }
}
