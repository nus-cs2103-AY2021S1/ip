package duke;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showHelpMessage();
    }
}
