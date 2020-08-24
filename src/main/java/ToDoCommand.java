import java.io.IOException;

class ToDoCommand extends Command {
    
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
