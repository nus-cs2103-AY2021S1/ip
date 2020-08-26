package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;


public class FindCommand extends Command{

    public FindCommand(String command) {
        super(command, false);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        TaskList finding = new TaskList();
        String keyWord = command.substring(5);
        for (Task t : list.getList()) {
            if (t.description.contains(keyWord)) {
                finding.add(t);
            }
        }
        ui.printFoundTask(finding);
    }
}
