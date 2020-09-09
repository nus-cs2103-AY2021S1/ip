public class HelpCommand extends Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasklist not found.";
        assert ui != null : "Ui not found.";
        assert storage != null : "Storage not found.";

        assert ui.showHelp() != null : "Message showing possible commands should be shown.";
        return ui.showHelp();
    }

    public boolean isExit() {
        return false;
    }
}
