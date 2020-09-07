package commands;

import ui.Ui;

/**
 * Exits from Duke.
 */
public class ByeCommand extends Command {

    private Ui ui;

    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public String execute() {
        System.exit(0);
        return this.ui.showExit();
    }

    @Override
    public String toString() {
        return "ByeCommand";
    }
}
