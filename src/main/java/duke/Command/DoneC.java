package duke.Command;

import duke.Command.Command;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class DoneC extends Command {

    private final String input;

    public DoneC(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList todoList, Storage store) throws IOException {
        String result = "";
        int taskID = Integer.parseInt( input.substring(5)) - 1;
        Task task = todoList.get(taskID);
        task.markAsDone();
        result += "Gratz, you finished this dawg :\n";
        result += task.toString();
        return result;
    }
}
