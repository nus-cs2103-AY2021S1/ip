public class DoneCommand extends Command {
    int idx;

    DoneCommand(int idx) {
        super();
        this.idx = idx;
    }

    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.markTaskAsDone(this.idx);
        storage.writeToFile(taskList);
    }
}
