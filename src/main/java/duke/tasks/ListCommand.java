package duke.tasks;

public class ListCommand extends Command{
    protected String list;

    public ListCommand(String str) {
        this.list = str;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        //gets the list of tasks
        tasks.list(this.list);
    }
}
