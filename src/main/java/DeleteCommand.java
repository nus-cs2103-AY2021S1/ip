import java.io.IOException;

class DeleteCommand extends Command {
    
    private final int argument;

    public DeleteCommand(int argument) {
        this.argument = argument;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException {
        ui.printToConsole(taskList.deleteTask(argument, storage));
    }
}
