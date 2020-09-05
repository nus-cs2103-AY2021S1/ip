package duke.command;

import java.util.List;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class SortCommand extends Command {

    public SortCommand() {
        super();
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return sortList(taskList);
    }

    public String sortList(TaskList taskList) {
        List<Task> list = taskList.sort();
        StringBuilder builder = new StringBuilder();
        builder.append("Here, I sorted the list for you : \n");
        int num = 1;

        for(Task t : list) {
            String taskString = num + ". " + t.toString();
            builder.append(taskString + "\n");
            num++;
        }
        return builder.toString();
    }
}

