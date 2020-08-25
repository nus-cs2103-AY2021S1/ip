public class doneCommand extends Command {
    int taskNo;

    public doneCommand(TaskList tasks, int taskNo) {
        super(tasks);
        this.taskNo = taskNo;
    }

    @Override
    public void execute() throws DukeException {
        this.tasks.markTaskDone(taskNo);
    }
}
