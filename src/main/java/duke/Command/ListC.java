package duke.Command;

import duke.Command.Command;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class ListC extends Command {


    @Override
    public String execute(Ui ui, TaskList todoList, Storage store){
        String result = "";
        result += "Here's your schedule lil dude\n";
        for (Task task :
                todoList.getTodoList()) {
            result += todoList.getID(task) + 1 + ". " + task.toString() + "\n";
        }
        if (todoList.size() == 0) {
            result += "Your life is empty now bruh";
        }
        return result;
    }
}
