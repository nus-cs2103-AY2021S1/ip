package duke.Command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class DoneC extends Command {

    public void execute(Ui ui, ArrayList<Task> todoList, Storage store){
        int taskID = ui.sc.nextInt() - 1;
        Task task = todoList.get(taskID);
        task.markAsDone();
        System.out.println("Gratz, you finished this dawg :");
        System.out.println(task.toString());
    }
}
