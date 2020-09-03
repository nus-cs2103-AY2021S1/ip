package duke.Command;

import duke.Command.Command;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class FindC extends Command {

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws IOException {
        String result = "";
        String keyword = ui.sc.nextLine();
        int findCount = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (Task found: todoList.todoList) {
            if (found.description.contains(keyword)) {
                result += findCount + 1 + "." + found.toString() + "\n";
                findCount++;
            }
        }
        if (findCount == 0) {
            result += "No related tasks found";
        }
        return result;
    }
}
