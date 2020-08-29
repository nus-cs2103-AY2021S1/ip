public class MarkAsDoneCommand extends Command {
    private int id;
    
    MarkAsDoneCommand(int id) {
        this.id = id;
    }

    @Override
    void execute(Storage storage, TaskList tasks, Ui ui) {
        try {
            Task task = tasks.get(id);
            tasks.delete(id);
            storage.save(tasks);
            ui.printStatus("    nice! I've marked this task as done:\n      " + task + "\n");
        } catch (IndexOutOfBoundsException e) {
            ui.printStatus("    oops! invalid task number!\n");
        }
    }
}
