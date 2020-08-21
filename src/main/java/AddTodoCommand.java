package main.java;

import java.io.IOException;

public class AddTodoCommand extends Command {
    public AddTodoCommand(String[] command) {
        super(command);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task temp = new Task (command[1]);
            tasks.addTask(temp);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
