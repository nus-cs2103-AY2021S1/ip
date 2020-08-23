package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        ui.printFindStatement();
        for (int i = 0; i < lib.size(); i++) {

            if (lib.get(i).getDescription().contains(keyWord)) {
                ui.showTask(lib.get(i).toString());
            }
        }
        ui.printEndLine();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
