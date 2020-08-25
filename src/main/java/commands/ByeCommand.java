package commands;

import ui.Ui;

/**
 * Exits from Duke.
 */
public class ByeCommand extends Command{

    private Ui ui;

    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        this.ui.showExit();
        System.exit(0);
    }

    @Override
    public String toString() {
        return "ByeCommand";
    }
}
