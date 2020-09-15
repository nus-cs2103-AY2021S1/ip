import java.io.IOException;

public class FindKeywordCommand extends Command{
    FindKeywordCommand(String str) {
        super(str);
    }

    /**
     * Deletes a specific task from the task list.
     *
     * @param list A list of task.
     * @param ui Ui that prints out the output.
     * @param storage Storage that reads from and writes to hard disk.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        TaskList ls = new TaskList();
        String s;
        try {
            String keyword = str.split(" ")[1];
            for (Task task : list.getList()) {
                if (task.getDes().contains(keyword)){
                    ls.addTask(task);
                }
            }
            s = ui.printFindTask(ls);
        } catch (IndexOutOfBoundsException | IOException e) {
            s = ui.printInvalidKeyword();
        }
        return s;
    }
}
