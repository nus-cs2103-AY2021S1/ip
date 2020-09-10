public class HelpCommand extends Command {
    /**
     * Shows a help message to users to show the possible commands that can be called.
     * @param tasks Task list of all tasks.
     * @param ui Ui to deal with interaction with user.
     * @param storage Storage to load and save tasks.
     * @return A help message with list of commands and description.
     */
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
