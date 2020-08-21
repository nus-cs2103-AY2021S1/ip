public class DeleteCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList list, Storage storage) {
        int count = list.count;
        int m = Parser.isValidDelete(input, count) - 1;
        Task toDelete = list.get(m);
        list.remove(toDelete);
        storage.deleteTask(list);
        ui.output("Noted. I've removed this task:\n\t    " + toDelete +
                "\n\t  Now you have " + list.size());
        list.count--;
    }
}
