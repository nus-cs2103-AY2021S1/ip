package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.Task.Task;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks.getList()) {
            System.out.println(index + ". " + task.toString());
            index++;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}