import java.io.IOException;

public class EventCommand extends Command {
    protected String event;

    public EventCommand(String event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //add event task to list of tasks
        tasks.event(this.event);

        //write to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);
    }
}
