public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = list.deleteTask(this.index);
        String textOutput = "Nice! I've marked this task as done:\n";
        textOutput += task;
        storage.updateTextFile(list);
        ui.printLine(textOutput);
    }
}
