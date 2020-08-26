public class DeleteCommand extends Command {
    private String taskNumber;

    public DeleteCommand(String taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            taskList.deleteTask(index);
            storage.saveTasks(taskList.getTask());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! The task number you entered does not exist.");
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.");
        }
    }
}
