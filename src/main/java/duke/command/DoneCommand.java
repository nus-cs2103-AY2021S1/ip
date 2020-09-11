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

        assert tasks.getTask(taskIndex).isDone() : "This task should be marked as completed";
    }

    @Override
    public String executeWithOutput(TaskList tasks, Ui ui) {
        tasks.getTask(taskIndex).doTask();
        assert tasks.getTask(taskIndex).isDone() : "This task should be marked as completed";

        return ui.getCompleteTaskResponseAsString();
    }

    @Override
    public void undo(TaskList tasks) {
        tasks.getTask(taskIndex).undoTask();
        assert !tasks.getTask(taskIndex).isDone() : "This task should be marked as incomplete";
    }
}
