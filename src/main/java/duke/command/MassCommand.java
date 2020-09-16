package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class MassCommand implements Command {

    private int numExecutions;
    private Command command;

    public MassCommand(int numExecutions, Command command) {
        this.numExecutions = numExecutions;
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        StringBuilder
        for (int i = 0; i < numExecutions; i++) {
            command.execute();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isMassCommand() {
        return true;
    }
}
