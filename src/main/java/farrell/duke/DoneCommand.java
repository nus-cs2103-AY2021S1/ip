package main.java.farrell.duke;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        type = CommandType.DONE;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        taskList.updateDone(taskIndex);
        return "Nice! I've marked the this as done.\n"
                + taskList.getTask(taskIndex).toString();
    }
}
