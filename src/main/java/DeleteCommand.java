public class DeleteCommand extends Command{
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber > taskList.size() || taskNumber < 1) {
            throw new DukeException("The task number is not found");
        }
        Task deletedTask = taskList.getTask(taskNumber);
        taskList.deleteTask(taskNumber);
        // display delete task success message
        String response = "Noted. I've removed this task:";
        response += "\n\t\t".concat(deletedTask.toString());
        response += String.format("\n\tNow you have %d tasks in the list.", taskList.size());
        ui.printResponse(response);
        // update task data in the file
        storage.writeFile(taskList);
    }
}
