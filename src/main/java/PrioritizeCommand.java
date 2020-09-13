public class PrioritizeCommand extends Command {
    PrioritizeCommand(String str) {
        super(str);
    }

    public String execute(TaskList list, Ui ui, Storage storage) {
        String s;
        try {
            int num = Integer.parseInt(str.split(" ")[1]);
            s = ui.printPrioritizeTask(list, num);
            Task impoTask = list.getList().get(num - 1);
            list.getList().remove(num - 1);
            list.getList().add(0, impoTask);
        } catch (IndexOutOfBoundsException e) {
            s = ui.printInvalidNumber();
        }
        return s;
    }
}
