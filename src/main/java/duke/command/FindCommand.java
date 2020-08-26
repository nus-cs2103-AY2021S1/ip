package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        List<Task> hits = new ArrayList<>();
        List<Integer> idxList = new ArrayList<>();

        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i-1).getDesc().contains(search)) {
                hits.add(tasks.get(i-1));
                idxList.add(i);
            }
        }

        ui.printSearchResult(search, idxList, hits);
    }
}
