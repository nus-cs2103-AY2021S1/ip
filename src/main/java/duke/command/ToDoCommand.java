package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.task.ToDo;

public class ToDoCommand implements Command {
    String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo toDo = ToDo.of(this.command);
        taskList.add(toDo);
        storage.appendToFile(toDo);
        ui.showTaskAdded(toDo);
    }

    public boolean isDone() {
        return false;
    }
}
