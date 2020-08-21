package main.java;

import java.io.IOException;

public class DoneCommand extends Command {

    public DoneCommand(String[] command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Integer toBeChanged = Integer.valueOf(command[1]);
            tasks.changeIsDone(toBeChanged);
            try {
                storage.saveFile(tasks);
            } catch (IOException e) {
            }
        } catch (DoneOutOfBoundException e) {
            throw e;
        } catch (NumberFormatException e) {
            throw new DoneUnknownException();
        } catch (IndexOutOfBoundsException e) {
            throw new DoneUnknownException();
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
