package duke.command;

import duke.component.*;

public class DoneCommand extends Command {
    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        int n = Parser.isValidDone(input, list.size()) - 1;
        list.get(n).markAsDone();
        storage.reWrite(list);
        ui.output("Nice! I've marked this task as done:\n\t    " + list.get(n));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DoneCommand) {
            return input.equals(((DoneCommand) obj).input);
        } else {
            return false;
        }
    }
}
