package main.java.duke;

public class UwuCommand extends Command {
    public static final String COMMAND_WORD = "uwu";
    public static final String MESSAGE_UWU_ACKNOWLEDGEMENT = "owo";

    public UwuCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(MESSAGE_UWU_ACKNOWLEDGEMENT);
    }
}
