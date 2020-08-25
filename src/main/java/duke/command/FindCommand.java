package duke.command;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.Task;
import duke.task.TaskException;
import duke.task.TaskList;

import java.util.List;

public class FindCommand extends Command {

    public FindCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskException, StorageException {
        List<Task> results = taskList.findTasks(args);
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            if (i != 0) {
                string.append("\n");
            }
            string.append(results.get(i).toString());
        }
        return string.toString();
    }
}
