public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if(tasks.isEmpty()) {
            ui.print("There are no items in the list right now.");
        } else {
           ui.print(tasks.itemize());
        }
    }
}
