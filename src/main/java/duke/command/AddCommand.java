package duke.command;

import duke.task.Task;
import duke.component.TaskList;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.Ui;

public class AddCommand extends Command {
    public AddCommand(String input) {
        super(input);
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        Task task = Parser.generate(input);
        storage.addToList(task);
        list.add(list.count++, task);
        String temp = list.count <= 1 ? " task" : " tasks";
        ui.output("Got it. I've added this task:\n\t    " + task +
                "\n\t  Now you have " + list.count + temp + " in the list.");
    }
}
