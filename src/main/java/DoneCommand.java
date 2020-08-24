public class DoneCommand extends Command {
    private int taskIndex;
    public DoneCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        try {
            Task editedTask = taskList.get(this.taskIndex);
            listStorage.editTask(editedTask, this.taskIndex, taskList);
            ui.markAsDone(this.taskIndex, taskList);
        } catch (IndexOutOfBoundsException ex) {
            try {
                throw new InvalidCommand("Please enter a valid task number.");
            } catch (InvalidCommand invalidCommand) {
                invalidCommand.printStackTrace();
            }
        }
    }
}
