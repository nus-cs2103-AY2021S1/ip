package duke;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "tasks have not been initialised";
        assert ui != null : "ui have not been initialised";
        assert storage != null : "storage have not been initialised";
        return ui.showOutput("Bye, hope to chat again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
