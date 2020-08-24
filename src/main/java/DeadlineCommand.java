public class DeadlineCommand extends AddTaskCommand {
    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void addTask(TaskList taskList, String input) throws DukeException {
        taskList.addDeadline(input);
    }
}
