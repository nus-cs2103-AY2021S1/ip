package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        String result = ui.printFindStatement();
        for (int i = 0; i < lib.size(); i++) {

            if (lib.get(i).getDescription().contains(keyWord)) {
                result += ui.showTask(lib.get(i).toString());
            }
        }
        return result;
    }

}
