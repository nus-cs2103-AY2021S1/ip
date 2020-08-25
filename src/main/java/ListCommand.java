import java.util.ArrayList;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getList();
        if (list.size() == 0) {
            System.out.println("There are no tasks in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < list.size() + 1; i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}