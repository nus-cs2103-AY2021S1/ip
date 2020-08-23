package duke.command;

/**
 * Represents a command that asks the program to add a new task to the list.
 */

import duke.task.Task;
import duke.component.*;

public class AddCommand extends Command {
    public AddCommand(String input) {
        super(input);
    }

    @Override
    public String execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        int count = list.size();
        Task task = Parser.generate(input);
        storage.addToList(task);
        list.add(count, task);
        String temp = count < 1 ? " task" : " tasks";
        String res = "Got it. I've added this task:\n\t    " + task +
                "\n\t  Now you have " + (count + 1) + temp + " in the list.";
        ui.output(res);
        return res;
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
