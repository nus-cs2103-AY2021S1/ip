public class deleteCommand extends Command {
    int taskNo;

    public deleteCommand(TaskList tasks, int taskNo) {
        super(tasks);
        this.taskNo = taskNo;
    }

    @Override
    public void execute() throws DukeException {
        this.tasks.deleteTask(taskNo);
    }
}
