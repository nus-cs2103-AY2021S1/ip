public class DoneCommand extends Command {
    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        int m = Parser.isValidDelete(input, list.count) - 1;
        Task toDelete = list.get(m);
        list.remove(toDelete);
        storage.deleteTask(list);
        ui.output("Noted. I've removed this task:\n\t    " + toDelete +
                "\n\t  Now you have " + list.size());
        list.count--;
    }
}
