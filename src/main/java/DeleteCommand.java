public class DeleteCommand extends Command {
    private int index;
    
    DeleteCommand (int index) {
        super(true);
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDeleteIndexException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidDeleteIndexException(tasks.size());
        }

        Task task = tasks.remove(index-1);
        storage.save(tasks);
        String response = String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", task, tasks.size());
        ui.printResponse(response);
    }

    @Override
    public String toString() {
        return "delete <task index>";
    }
}
