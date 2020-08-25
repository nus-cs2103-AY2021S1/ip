public class EventCommand extends TaskCreationCommand {
    final static String COMMAND = "event";
    final static String TIME_SPECIFIER = "/at";
    
    private String description;
    private String time;
    
    EventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, time);
        super.execute(task, ui, tasks);
    }
}
