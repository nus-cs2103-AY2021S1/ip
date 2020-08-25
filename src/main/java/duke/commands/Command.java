package src.main.java.duke.commands;

import java.util.List;
import src.main.java.duke.data.Duke;
import src.main.java.duke.data.task.Task;

public class Command {

    protected Duke duke;
    protected List<Task> taskList;
    private int targetIndex = -1;

    /**
     * @param targetIndex last visible listing index of the target task
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(Duke duke) {
        this.duke = duke;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
