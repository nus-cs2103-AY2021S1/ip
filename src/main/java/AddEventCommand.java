public class AddEventCommand extends AddCommand {
    protected String at;

    public AddEventCommand(String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(taskDescription, at);
        tasks.add(newTask);
        ui.showAdded(newTask, tasks.size());
        
        storage.save(tasks);
    }
}
