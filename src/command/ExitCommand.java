package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.Arrays;

public class ExitCommand extends Command {

    public ExitCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ExitCommand) {
            ExitCommand other = (ExitCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}
