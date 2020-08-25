/**
 * Represents an exit command. Program will exit after the command is executed.
 */
public class ExitCommand extends Command {

    /**
     * Public constructor.
     */
    public ExitCommand() {
    }

    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayOutput(Ui.MESSAGE_EXIT);
        exit();
    }
}
