package duke;

public class DeleteCommand implements Command{
    int indexToBeDeleted;
    public DeleteCommand(int indexToBeDeleted) {
        this.indexToBeDeleted = indexToBeDeleted;
    }

    @Override
    public void execute(TaskList tasks) {
        // Do TaskList stuff
        Task taskToBeDeleted = tasks.getTask(indexToBeDeleted);
        tasks.delete(indexToBeDeleted);
        // Do UI stuff
        Ui.printDelete(taskToBeDeleted.getDescription(), tasks.length());
        // Do storage stuff
        // tbc
    }
}
