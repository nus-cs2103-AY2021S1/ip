package duke;

/**
 * Represents a help command.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "tasks have not been initialised";
        assert ui != null : "ui have not been initialised";
        assert storage != null : "storage have not been initialised";
        StringBuilder output = new StringBuilder();
        output.append(ui.getInstructions());
        return ui.showOutput(output.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
