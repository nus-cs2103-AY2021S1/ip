public class ListCommand extends Command {
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.showTaskList();
        storage.writeToFile(taskList);
    }
}
