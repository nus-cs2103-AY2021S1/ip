public class HelpCommand implements Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeError {
        ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
