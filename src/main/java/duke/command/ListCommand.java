package duke.command;

import duke.Storage;
import duke.Ui;

public class ListCommand implements Command {

    public void execute(String command, Storage s, Ui ui) {
        s.printAll();
    }

}
