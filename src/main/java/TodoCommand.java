import java.util.ArrayList;

public class TodoCommand extends Command {
    private final String TAB = "  ";
    private final String ADD_TASK_TITLE = TAB + " Got it. I've added this task:";
    private String[] input;
    
    public TodoCommand(String[] input) {
        super();
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TodoException{
        ArrayList<Task> store = tasks.getTaskList();
        if (input.length == 1) {
            throw new TodoException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo newTask = new Todo(input[2]);

        store.add(newTask);
        storage.save(new TaskList(store));
        
        System.out.println(ADD_TASK_TITLE);
        System.out.println(TAB + "   " + newTask);
        System.out.println(TAB + " Now you have " + store.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

