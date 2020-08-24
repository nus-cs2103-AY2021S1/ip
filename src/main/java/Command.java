import java.io.IOException;

abstract class Command {
    
    public abstract void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException;
    
}
