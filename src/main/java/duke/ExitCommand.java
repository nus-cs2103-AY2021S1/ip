package duke;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showOutput("Bye, hope to chat again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
