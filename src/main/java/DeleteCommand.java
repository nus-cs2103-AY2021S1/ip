import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final String TAB = "  ";
    private final String DELETE_TITLE = TAB + " Noted. I've removed this task:";

    private String[] input;
    
    public DeleteCommand(String[] input) {
        super();
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeleteException {
        ArrayList<Task> store = tasks.getTaskList();
        if (this.input.length == 1) { //incomplete done command
            throw new DeleteException(" ☹ OOPS!!! The description of a delete cannot be empty.");
        }

        int indexOfMarkingTask = Integer.parseInt(this.input[1]) - 1;
        if (indexOfMarkingTask + 1 > store.size() || indexOfMarkingTask + 1 <= 0) {
            throw new DeleteException(" ☹ OOPS!!! There is no such task.");
        }

        //complete done command
        Task deletingTask = store.get(indexOfMarkingTask);
        store.remove(indexOfMarkingTask);
        storage.save(new TaskList(store));

        System.out.println(DELETE_TITLE);
        System.out.println(TAB + "   " + deletingTask);
        System.out.println(TAB + " Now you have " + store.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
