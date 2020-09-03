package duke.commands;


import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.getTasks(taskList);
    }
}
