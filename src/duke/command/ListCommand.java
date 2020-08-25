package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            processList(taskList);
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }

    public void processList(TaskList taskList) throws DukeException {
        try {
            taskList.listItems();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please input a valid number.");
        }
    }
}
