public class ListCommand extends Command {
    public void execute(TaskList lst, Ui ui, Storage storage) {
        for (int i = 0; i < lst.size(); i++) {
            int num = i + 1;
            ui.showTask(lst.get(i), num);
        }
        ui.showLine();
    }
}
