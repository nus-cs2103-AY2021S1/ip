package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.TodoTask;

public class TodoCommand implements Command {

    String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        Task todoTask = new TodoTask(description);
        tasks.add(todoTask);
        ui.printWithWrapper(new ArrayList<>(List.of(
                "Sure! I have added the following todo task to your list: ",
                todoTask.toString(),
                tasks.getListStatus())), false, false);
    }
}
