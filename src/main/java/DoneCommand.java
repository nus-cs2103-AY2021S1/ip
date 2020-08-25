import java.util.ArrayList;

public class DoneCommand extends Command {
    private final String TAB = "  ";
    private final String DONE_TITLE = TAB + " Nice! I've marked this task as done:";
    private String[] input;
    
    public DoneCommand(String[] input) {
        super();
        this.input = input;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoneException {
        ArrayList<Task> store = tasks.getTaskList();
        
        if (input.length == 1) { //incomplete done command
            throw new DoneException(" ☹ OOPS!!! The description of a done cannot be empty.");
        }

        int indexOfMarkingTask = Integer.parseInt(input[1]) - 1;
        if (indexOfMarkingTask + 1 > store.size() || indexOfMarkingTask + 1 <= 0) {
            throw new DoneException(" ☹ OOPS!!! There is no such task.");
        }

        //complete done command
        Task markingTask = store.get(indexOfMarkingTask);
        Task markedTask = markingTask.markAsDone();
        store.set(indexOfMarkingTask, markedTask);
        storage.save(new TaskList(store));

        System.out.println(DONE_TITLE);
        System.out.println(TAB + "   " + markedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
