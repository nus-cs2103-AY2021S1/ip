public class HelpCommand extends Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }

    public boolean isExit() {
        return false;
    }
}
