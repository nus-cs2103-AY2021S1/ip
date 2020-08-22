import java.io.IOException;

public class DeleteCommand extends Command {
    protected String delete;

    public DeleteCommand(String delete) {
        this.delete = delete;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //add event task to list of tasks
        tasks.delete(this.delete);

        //write to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);
    }
}
