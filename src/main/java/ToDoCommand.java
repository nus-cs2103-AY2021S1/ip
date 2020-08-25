public class ToDoCommand extends TaskCreationCommand {
    final static String COMMAND = "todo";
    
    private String description;
    
    ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(description);
        super.execute(task, ui, tasks);
    }
}
