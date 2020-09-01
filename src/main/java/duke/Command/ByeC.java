package duke.Command;

import duke.Command.Command;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class ByeC extends Command {

    public void execute(Ui ui, TaskList todoList, Storage store){
        System.out.println("See you later alligator ");
        System.exit(0);
    }
}
