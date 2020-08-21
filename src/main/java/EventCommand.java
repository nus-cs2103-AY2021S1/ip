public class EventCommand extends Command {
    private final String description;
    private final String at;


    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            tasks.addTask(new Events(description, at));
        } catch (Exception ex) {
            throw new DukeException("Error creating this event");
        }
    }


}