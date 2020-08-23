package duke.command;

import duke.Storage;
import duke.Ui;

public class ListCommand implements Command {

    public void execute(String command, Storage s, Ui ui) {
        s.printAll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else return o instanceof ListCommand;
    }
}
