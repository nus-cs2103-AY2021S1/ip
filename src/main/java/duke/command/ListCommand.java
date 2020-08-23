package duke.command;

import duke.component.*;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public String execute(Ui ui, TaskList list, Storage storage) {
        ui.printList(list, t -> true, "");
        return "list";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
