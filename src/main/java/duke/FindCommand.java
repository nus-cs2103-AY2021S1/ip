package duke;

import java.util.ArrayList;
import java.io.IOException;

public class FindCommand extends Command {
    private String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList.getList()) {
            if (task.getName().contains(this.keyword)) {
                list.add(task);
            }
        }
        if (list.size() == 0) {
            System.out.println("No tasks matching the keyword");
        } else {
            System.out.println("Here are the matching tasks in your list");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ": " + list.get(i));
            }
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
