public class DoneCommand extends Command {

    private final String taskNumber;

    public DoneCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task task = manager.getTasks().get(index);
            manager.markTaskAsDone(task);
            ui.showDoneMessage(task);
            storage.saveTasks(manager.getTasks());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            // Invalid task number or number out of range
            String errorMessage = "Invalid task number! " + "Please enter a valid task number :)\n";
            throw new DukeException(errorMessage);
        }
    }
    
}