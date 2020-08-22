import java.io.IOException;

public class TodoCommand extends Command{
    protected String todo;

    public TodoCommand(String todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //add todo task to list of tasks
        tasks.todo(this.todo);

        //write task to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);
    }
}
