import java.util.ArrayList;

public class EventCommand extends Command {

    private String description;
    private String time;

    public EventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        try {
            Event event = new Event(description, time);
            tasks.addEvent(event);
            ui.printAddStatements(event.toString(), lib.size());
        } catch (DukeException ex) {
            ui.printExceptions(ex);
        }

    }

    @Override
    public boolean isDone() {
        return false;
    }

}
