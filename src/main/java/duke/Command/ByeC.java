package duke.Command;

import duke.Command.Command;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class ByeC extends Command {

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store){
        String result = "See you later alligator!";
        System.exit(0);
        return result;
    }
}
