package main.java;

import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Integer toBeDeleted= Integer.valueOf(command[1]);
            tasks.delete(toBeDeleted);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
            }
        } catch (DeleteOutOfBoundException e) {
            throw e;
        } catch (NumberFormatException e) {
            throw new DeleteUnknownException();
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteUnknownException();
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
