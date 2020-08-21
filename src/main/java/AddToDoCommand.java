package main.java;

import java.io.IOException;

public class AddToDoCommand extends Command {

    public AddToDoCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String description = splitCommand[1];
            Task toAdd = new ToDo(description);
            tasks.add(toAdd);
            ui.sayAddedTask(toAdd, tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            ui.sayException(e);
        } catch (IndexOutOfBoundsException e) { // No description
            throw new EmptyTodoException();
        }
    }

    public boolean isExit() {
        return false;
    }
}
