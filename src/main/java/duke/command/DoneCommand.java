public class DoneCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException {
        int taskId = Parser.parseTaskId(input);

        if (taskId < 0 || taskId >= taskList.taskSize())
            throw new InvalidTaskIdException(taskId + 1 + "");

        Task task = taskList.markAsDone(taskId);
        ui.markAsDone(task);
    }
}
