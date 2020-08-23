package duke.Command;

import duke.Exception.ToDoException;
import duke.Storage;
import duke.Task.Task;
import duke.Task.ToDo;
import duke.TaskList;
import duke.Ui;

public class ToDoCommand extends Command {
    public ToDoCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ToDoException {
        Task task = new ToDo(input);
        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
