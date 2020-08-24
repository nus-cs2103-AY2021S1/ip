public class AddToDoCommand extends AddCommand {

    protected AddToDoCommand(String taskDetails) {
        super(taskDetails);
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskDetails.isEmpty()) {
            throw new EmptyTaskException(TaskType.TODO);
        } else {
            addTask(new ToDo(taskDetails), tasks, ui, storage);
        }
    }
}
