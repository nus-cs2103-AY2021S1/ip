public class AddToDoCommand extends AddCommand {
    AddToDoCommand(String taskDescription) {
        super(taskDescription);
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(taskDescription);
        tasks.add(newTask);
        ui.showAdded(newTask, tasks.size());
        
        storage.save(tasks);
    }
}
