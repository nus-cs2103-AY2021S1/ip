package duke.Command;

import duke.Exception.FindException;
import duke.Storage;
import duke.Task.Task;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    public FindCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FindException {
        System.out.println("Here are the matching tasks in your list:");
        int index = 1;
        for (Task task : tasks.getList()) {
            if (task.toString().contains(input)) {
                System.out.println(index + ". " + task.toString());
                index++;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
