public class DeleteCommand extends Command{

    DeleteCommand(String str) {
        super(str);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            int num = Integer.parseInt(str.split(" ")[1]);
            ui.printDelete(list, num);
            list.getList().remove(num-1);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidNumber();
        }
    }
}
