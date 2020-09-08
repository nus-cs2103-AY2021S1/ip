package duke.command;

import duke.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(new Ui().availableCommands());
    }
}
