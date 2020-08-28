public class DeleteCommand extends Command {
    private int number;
    
    public DeleteCommand(int number) {
        this.number = number;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        try {
            Task task = taskList.get(number);
            taskList.deleteTask(number);
            store.write(taskList);
            ui.showDeletion(task, taskList);
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid index for the task to be deleted");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task not found in list");
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
