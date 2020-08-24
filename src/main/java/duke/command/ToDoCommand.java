package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.IOException;

public class ToDoCommand extends Command {
    
    private final String argument;

    public ToDoCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException {
        ToDo newTodo = ToDo.createNewToDo(argument);
        storage.writeLineToStorage(newTodo.generateStorageString());
        ui.printToConsole(taskList.addTaskToList(newTodo));
    }
}
