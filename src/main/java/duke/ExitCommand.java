package duke;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showOutput("Bye, hope to chat again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
