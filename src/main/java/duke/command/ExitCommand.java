package duke.command;

import duke.Storage;
import duke.Ui;

public class ExitCommand implements Command {

    public void execute(String command, Storage s, Ui ui) {
        ui.goodBye();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else return o instanceof ExitCommand;
    }
}
