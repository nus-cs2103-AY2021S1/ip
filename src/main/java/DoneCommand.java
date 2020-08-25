public class DoneCommand extends Command {
    private int index;
    
    DoneCommand(int index) {
        super(true);
        this.index = index;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDoneIndexException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidDoneIndexException(tasks.size());
        }
        
        Task task = tasks.get(index-1);
        task.completeTask();
        storage.save(tasks);
        String response = "Nice! I've marked this task as done:\n  " + task.toString();
        ui.printResponse(response);
    }

    @Override
    public String toString() {
        return "done <task index>";
    }
}
