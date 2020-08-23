package duke.command;

import duke.task.Task;
import duke.component.*;

public class AddCommand extends Command {
    public AddCommand(String input) {
        super(input);
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        int count = list.size();
        Task task = Parser.generate(input);
        storage.addToList(task);
        list.add(count, task);
        String temp = count <= 1 ? " task" : " tasks";
        ui.output("Got it. I've added this task:\n\t    " + task +
                "\n\t  Now you have " + count + temp + " in the list.");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof AddCommand) {
            return input.equals(((AddCommand) obj).input);
        } else {
            return false;
        }
    }
}
