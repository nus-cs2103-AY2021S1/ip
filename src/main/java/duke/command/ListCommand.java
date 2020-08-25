package duke.command;

import duke.storage.Storage;
import duke.util.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    protected static final String LIST_HEADER = "Here are the tasks in your list: ";

    public static ListCommand create() {
        return new ListCommand();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.outputMessage(LIST_HEADER + '\n' + taskList.toString());
    }


}
