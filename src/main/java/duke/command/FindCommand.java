package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : tasks.getList()) {
            if (task.getName().contains(this.keyword)) {
                list.add(task);
            }
        }
        if (list.size() == 0) {
            System.out.println("There are no task which matches this keyword");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : list) {
                System.out.println(task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}