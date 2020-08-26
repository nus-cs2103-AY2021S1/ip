package duke.command;

import duke.main.TaskList;
import duke.task.Task;

public class DeleteCommand extends Command{

    protected int taskNumber;

    public DeleteCommand(String taskNumber) {
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    @Override
    public void perform(TaskList tasks) {
        Task task = tasks.get(taskNumber - 1);
        tasks.delete(taskNumber - 1);
        System.out.println(" Okie! I have deleted this task: " + "\n"
                + "   " + task.toString() + "\n" + " Now you have " + tasks.size() + (tasks.size() > 1
                ? " tasks." : " task."));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
