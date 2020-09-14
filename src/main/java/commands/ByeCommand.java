package commands;

import ui.Ui;

/**
 * Exits from model.Duke.
 */
public class ByeCommand extends Command {

    public ByeCommand(Ui ui) {
        super(ui);
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
