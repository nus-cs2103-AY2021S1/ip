package main.java.farrell.duke;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        type = CommandType.DELETE;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        Task deleteTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        return  "I've removed this task:\n"
                + deleteTask.toString();
    }
}
