public class TodoCommand extends Command {
    private String description;
    
    public TodoCommand(String description) {
        super();
        this.description = description;
    }
    
    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        taskList.addTask(new Todo(description));
        storage.saveTasks(taskList.getTask());
    }
}
