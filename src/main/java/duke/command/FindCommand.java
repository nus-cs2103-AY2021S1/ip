package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : tasks.getList()) {
            if (task.getName().contains(keyword)) {
                list.add(task);
            }
        }
        if (list.size() == 0) {
            ui.setResponse("There is no task which matches this keyword");
        } else {
            String response = "Here are the matching tasks in your list:\n";
            for (Task task : list) {
                response += task + "\n";
            }
            ui.setResponse(response);
        }
    }
}
