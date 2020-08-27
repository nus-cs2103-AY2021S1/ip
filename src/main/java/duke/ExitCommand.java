package main.java.duke;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "bb cya again!";

    public ExitCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
