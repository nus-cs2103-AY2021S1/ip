package duke.command;

import duke.InvalidCommandException;
import duke.task.Task;
import duke.component.TaskList;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        int count = list.count;
        int m = Parser.isValidDelete(input, count) - 1;
        Task toDelete = list.get(m);
        list.remove(toDelete);
        storage.deleteTask(list);
        ui.output("Noted. I've removed this task:\n\t    " + toDelete +
                "\n\t  Now you have " + list.size());
        list.count--;
    }
}
