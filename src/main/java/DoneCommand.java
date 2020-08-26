public class DoneCommand extends Command{

    DoneCommand(String str) {
        super(str);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            int num = Integer.parseInt(str.split(" ")[1]);
            list.getList().get(num-1).markDone();
            ui.printDone(list, num);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidNumber();
        }
    }
}
