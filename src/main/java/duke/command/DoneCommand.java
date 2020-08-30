package duke.command;

import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayCompleteTask();
        tasks.getTask(taskIndex).doTask();
    }

    @Override
    public String executeWithOutput(TaskList tasks, Ui ui) {
        tasks.getTask(taskIndex).doTask();
        return ui.getCompleteTaskResponseAsString();
    }
}
