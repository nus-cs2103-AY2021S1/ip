package duke.command;

import duke.InvalidCommandException;
import duke.component.TaskList;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.Ui;

public class DoneCommand extends Command {
    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        int n = Parser.isValidDone(input, list.count) - 1;
        list.get(n).markAsDone();
        ui.output("Nice! I've marked this task as done:\n\t    " + list.get(n));
    }
}
